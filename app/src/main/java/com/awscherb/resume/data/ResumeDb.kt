package com.awscherb.resume.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        BulletEntity::class,
        EducationEntity::class,
        JobEntity::class,
        ResumeEntity::class,
        RoleEntity::class,
        ProjectsMetaEntity::class,
        ProjectEntity::class,
    ],
    version = 10,
    exportSchema = true
)
abstract class ResumeDb : RoomDatabase() {
    abstract fun resumeDao(): ResumeDao

    abstract fun projectDao(): ProjectDao
}