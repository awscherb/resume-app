package com.awscherb.resume.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class ResumeEntity(
    @PrimaryKey
    val version: Int,
    val name: String,
    val email: String,
    val github: String,
    val versionDate: Long,
) {
    constructor(resumeResponse: ResumeResponse) : this(
        version = resumeResponse.version,
        name = resumeResponse.name,
        email = resumeResponse.email,
        github = resumeResponse.github,
        versionDate = resumeResponse.versionDate
    )
}

@Entity
data class JobEntity(
    @PrimaryKey
    val order: Int,
    val company: String,
    val url: String,
    val location: String,
    val resumeVersion: Int,
    val startMonth: Int,
    val startYear: Int,
    val endMonth: Int?,
    val endYear: Int?,
    val latest: Boolean
) {
    constructor(jobResponse: JobsResponse, version: Int) : this(
        order = jobResponse.order,
        company = jobResponse.company,
        url = jobResponse.url,
        location = jobResponse.location,
        resumeVersion = version,
        startMonth = jobResponse.startMonth,
        startYear = jobResponse.startYear,
        endMonth = jobResponse.endMonth,
        endYear = jobResponse.endYear,
        latest = jobResponse.latest
    )
}


@Entity(primaryKeys = ["jobId", "resumeVersion", "order"])
data class RoleEntity(
    val title: String,
    val jobId: Int,
    val resumeVersion: Int,
    val startMonth: Int,
    val startYear: Int,
    val endMonth: Int?,
    val endYear: Int?,
    val latest: Boolean,
    val order: Int,
) {
    constructor(
        roleResponse: RoleResponse,
        version: Int,
        jobId: Int
    ) : this(
        title = roleResponse.title,
        startMonth = roleResponse.startMonth,
        startYear = roleResponse.startYear,
        endMonth = roleResponse.endMonth,
        endYear = roleResponse.endYear,
        latest = roleResponse.latest,
        jobId = jobId,
        order = roleResponse.order,
        resumeVersion = version
    )
}

@Entity(primaryKeys = ["jobId", "resumeVersion", "order"])
data class BulletEntity(
    val order: Int,
    val jobId: Int,
    val resumeVersion: Int,
    val text: String
)

@Entity(primaryKeys = ["resumeVersion", "order"])
data class EducationEntity(
    val institution: String,
    val subinstitution: String?,
    val result: String,
    val resultYear: Int,
    val resumeVersion: Int,
    val order: Int
) {
    constructor(response: EducationResponse, version: Int) : this(
        institution = response.institution,
        subinstitution = response.subinstitution,
        result = response.result,
        resultYear = response.resultYear,
        resumeVersion = version,
        order = response.order
    )
}

@Entity
data class ProjectsMetaEntity(
    @PrimaryKey
    val version: Int,
    val versionDate: Long
)

@Entity(primaryKeys = ["name", "order"])
data class ProjectEntity(
    val name: String,
    val shortDescription: String,
    val longDescription: String?,
    val mainLink: String,
    val secondaryLink: String?,
    val order: Int,
    val metaVersion: Int
)