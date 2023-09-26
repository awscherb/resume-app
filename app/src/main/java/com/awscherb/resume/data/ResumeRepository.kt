package com.awscherb.resume.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ResumeRepository @Inject constructor(
    private val resumeApi: ResumeApi,
    private val resumeDao: ResumeDao
) {

    fun fetchResume(): Flow<LoadState<Resume>> {
        return flow {
            emit(
                try {
                    Result.success(resumeApi.fetchResume())
                } catch (e: Exception) {
                    Result.failure(e)
                }
            )
        }.map { result ->
            when (result.isSuccess) {
                false -> Result.failure(result.exceptionOrNull()!!)
                true -> {
                    val resumeResponse = result.getOrThrow()
                    val resume = ResumeEntity(resumeResponse)

                    val roles = mutableListOf<RoleEntity>()
                    val bullets = mutableListOf<BulletEntity>()
                    val jobs = mutableListOf<JobEntity>()

                    resumeResponse.jobs.forEach { jobResponse ->
                        val job = JobEntity(jobResponse, resume.version)

                        roles += jobResponse.roles.map { roleResponse ->
                            RoleEntity(
                                roleResponse,
                                jobId = job.order,
                                version = resumeResponse.version
                            )
                        }

                        bullets += jobResponse.bullets.map { bulletResponse ->
                            BulletEntity(
                                order = bulletResponse.order,
                                jobId = job.order,
                                text = bulletResponse.text,
                                resumeVersion = resumeResponse.version
                            )
                        }

                        jobs += job
                    }

                    val education = resumeResponse.education.map {
                        EducationEntity(it, resumeResponse.version)
                    }

                    Result.success(Holder(resume, jobs, roles, bullets, education))
                }
            }
        }
            .map { result: Result<Holder> ->
                when (result.isSuccess) {
                    false -> Result.failure(result.exceptionOrNull()!!)
                    true -> try {
                        val (resume, jobs, roles, bullets, education) = result.getOrThrow()
                        resumeDao.insert(resume, jobs, roles, bullets, education)
                        Result.success(Unit)
                    } catch (e: Exception) {
                        Result.failure(e)
                    }
                }
            }.flatMapLatest {
                try {
                    resumeDao.fetchResume().map { result ->
                        if (result.isEmpty()) {
                            LoadState.Error(Throwable("Couldn't load resume"))
                        } else {
                            val resume = result[0]
                            LoadState.Success(
                                resume.toModel(
                                    resumeDao.fetchJobsForResume(resume.version),
                                    resumeDao.fetchRolesForResume(resume.version),
                                    resumeDao.fetchBulletsForJob(resume.version),
                                    resumeDao.fetchEducation(resume.version)
                                )
                            )
                        }
                    }
                } catch (e: Exception) {
                    flowOf(LoadState.Error(e))
                }
            }
    }
}

private data class Holder(
    val resume: ResumeEntity,
    val jobs: List<JobEntity>,
    val roles: List<RoleEntity>,
    val bullets: List<BulletEntity>,
    val education: List<EducationEntity>
)