package com.awscherb.resume.data

data class ResumeResponse(
    val name: String,
    val email: String,
    val github: String,
    val version: Int,
    val versionDate: Long,
    val jobs: List<JobsResponse>,
    val education: List<EducationResponse>
)

data class JobsResponse(
    val company: String,
    val url: String,
    val location: String,
    val startMonth: Int,
    val startYear: Int,
    val endMonth: Int?,
    val endYear: Int?,
    val latest: Boolean,
    val order: Int,
    val roles: List<RoleResponse>,
    val bullets: List<BulletResponse>
)

data class RoleResponse(
    val title: String,
    val startMonth: Int,
    val startYear: Int,
    val endMonth: Int?,
    val endYear: Int?,
    val latest: Boolean,
    val order: Int
)

data class BulletResponse(
    val text: String,
    val order: Int
)

data class EducationResponse(
    val institution: String,
    val subinstitution: String?,
    val result: String,
    val resultYear: Int,
    val order :Int
)