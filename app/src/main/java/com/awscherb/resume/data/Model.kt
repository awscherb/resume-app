package com.awscherb.resume.data

import java.util.Date

data class Resume(
    val name: String,
    val email: String,
    val github: String,
    val version: Int,
    val versionDate: Long,
    val jobs: List<Job>,
    val education: List<Education>
)

data class Job(
    val company: String,
    val url: String,
    val location: String,
    val startMonth: Int,
    val startYear: Int,
    val endMonth: Int?,
    val endYear: Int?,
    val latest: Boolean,
    val order: Int,
    val roles: List<Role>,
    val bullets: List<Bullet>
)

data class Role(
    val title: String,
    val startMonth: Int,
    val startYear: Int,
    val endMonth: Int?,
    val endYear: Int?,
    val latest: Boolean
)

fun RoleEntity.toRole() = Role(
    title, startMonth, startYear, endMonth, endYear, latest
)

data class Bullet(
    val text: String,
    val order: Int,
)

fun BulletEntity.toBullet() = Bullet(text, order)

data class Education(
    val institution: String,
    val subinstitution: String?,
    val result: String,
    val resultYear: Int
)

fun EducationEntity.toEducation() =
    Education(institution, subinstitution, result, resultYear)

fun ResumeEntity.toModel(
    jobs: List<JobEntity>,
    roles: List<RoleEntity>,
    bullets: List<BulletEntity>,
    education: List<EducationEntity>
): Resume {
    val jobList = mutableListOf<Job>()
    val roleList = mutableMapOf<Int, List<Role>>()
    val bulletList = mutableMapOf<Int, List<Bullet>>()

    jobs.forEach { job ->
        bulletList += bullets.groupBy { it.jobId }
            .mapValues { (_, v) -> v.map { it.toBullet() } }

        roleList += roles.groupBy { it.jobId }
            .mapValues { (_, v) -> v.map { it.toRole() } }

        jobList += Job(
            company = job.company,
            url = job.url,
            location = job.location,
            startMonth = job.startMonth,
            startYear = job.startYear,
            endMonth = job.endMonth,
            endYear = job.endYear,
            latest = job.latest,
            roles = roleList[job.order] ?: emptyList(),
            bullets = bulletList[job.order] ?: emptyList(),
            order = job.order
        )
    }

    return Resume(
        name = name,
        email = email,
        github = github,
        version = version,
        versionDate = versionDate,
        jobs = jobList,
        education = education.map { it.toEducation() }
    )
}

data class Projects(
    val version: Int,
    val versionDate: Date,
    val projects: List<Project>
)

data class Project(
    val name: String,
    val shortDescription: String,
    val longDescription: String?,
    val mainLink: String,
    val secondaryLink: String?,
    val order: Int,
)