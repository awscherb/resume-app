package com.awscherb.resume.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface ResumeDao {

    @Transaction
    suspend fun insert(
        resume: ResumeEntity,
        job: List<JobEntity>,
        roles: List<RoleEntity>,
        bullets: List<BulletEntity>,
        education: List<EducationEntity>
    ) {
        insertResume(resume)
        insertJob(job)
        insertRoles(roles)
        insertBullets(bullets)
        insertEducation(education)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResume(resume: ResumeEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(jobs: List<JobEntity>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoles(roles: List<RoleEntity>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBullets(bullets: List<BulletEntity>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEducation(education: List<EducationEntity>): LongArray

    @Query("SELECT * FROM ResumeEntity ORDER BY version DESC LIMIT 1")
    fun fetchResume(): Flow<List<ResumeEntity>>

    @Query("SELECT * FROM JobEntity WHERE resumeVersion = :resumeVersion ORDER BY `order` ASC")
    suspend fun fetchJobsForResume(resumeVersion: Int): List<JobEntity>

    @Query("SELECT * FROM RoleEntity WHERE resumeVersion = :resumeVersion")
    suspend fun fetchRolesForResume(resumeVersion: Int): List<RoleEntity>

    @Query("SELECT * FROM BulletEntity WHERE resumeVersion = :resumeVersion")
    suspend fun fetchBulletsForJob(resumeVersion: Int): List<BulletEntity>

    @Query("SELECT * FROM EducationEntity WHERE resumeVersion = :resumeVersion")
    suspend fun fetchEducation(resumeVersion: Int): List<EducationEntity>
}