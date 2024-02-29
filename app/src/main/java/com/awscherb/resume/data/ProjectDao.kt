package com.awscherb.resume.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ProjectDao {

    @Transaction
    suspend fun insert(
        projectsMetaEntity: ProjectsMetaEntity,
        projects: List<ProjectEntity>
    ) {
        insertMeta(projectsMetaEntity)
        insertProjects(projects)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeta(projectsMetaEntity: ProjectsMetaEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProjects(projects: List<ProjectEntity>): LongArray

    @Query("SELECT * FROM ProjectsMetaEntity ORDER BY version DESC LIMIT 1")
    suspend fun fetchProjectsMeta(): List<ProjectsMetaEntity>

    @Query("SELECT * FROM ProjectEntity WHERE metaVersion = :metaVersion ORDER BY `order` ASC")
    suspend fun fetchProjects(metaVersion: Int): List<ProjectEntity>
}