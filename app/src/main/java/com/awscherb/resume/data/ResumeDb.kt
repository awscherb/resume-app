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
    ],
    version = 9,
    exportSchema = true
)
abstract class ResumeDb : RoomDatabase() {
    abstract fun resumeDao(): ResumeDao
}