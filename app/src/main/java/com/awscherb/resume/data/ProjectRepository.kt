package com.awscherb.resume.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject

class ProjectRepository @Inject constructor(
    private val projectsApi: ProjectsApi,
    private val projectDao: ProjectDao
) {

    fun fetchProjects(): Flow<LoadState<Projects>> {
        return flow {
            emit(
                try {
                    Result.success(projectsApi.fetchProjects())
                } catch (e: Exception) {
                    Result.failure(e)
                }
            )
        }.map { result ->
            when (result.isSuccess) {
                false -> Result.failure(result.exceptionOrNull()!!)
                true -> {
                    val projectsResponse = result.getOrThrow()

                    val projectEntities = projectsResponse.projects.map {
                        ProjectEntity(
                            name = it.name,
                            shortDescription = it.shortDescription,
                            longDescription = it.longDescription,
                            mainLink = it.mainLink,
                            secondaryLink = it.secondaryLink,
                            order = it.order,
                            metaVersion = projectsResponse.version
                        )
                    }

                    Result.success(

                        ProjectsMetaEntity(
                            version = projectsResponse.version,
                            versionDate = projectsResponse.versionDate
                        ) to projectEntities
                    )
                }
            }
        }
            .map { result ->
                when (result.isSuccess) {
                    false -> Result.failure(result.exceptionOrNull()!!)
                    true -> {
                        val (projectMeta, projects) = result.getOrThrow()
                        projectDao.insert(projectMeta, projects)
                        Result.success(Unit)
                    }
                }
            }
            .map { projectDao.fetchProjectsMeta() }
            .filter { it.isNotEmpty() }
            .map { it[0] }
            .map { meta ->
                meta to projectDao.fetchProjects(meta.version)
            }
            .map { (meta, projects) ->
                LoadState.Success(
                    Projects(version = meta.version,
                        versionDate = Date(meta.versionDate),
                        projects = projects.map {
                            Project(
                                name = it.name,
                                shortDescription = it.shortDescription,
                                longDescription = it.longDescription,
                                mainLink = it.mainLink,
                                secondaryLink = it.secondaryLink,
                                order = it.order
                            )
                        })
                )
            }
    }
}
