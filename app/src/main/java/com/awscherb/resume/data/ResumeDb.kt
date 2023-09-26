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
    version = 9
)
abstract class ResumeDb : RoomDatabase() {
    abstract fun resumeDao(): ResumeDao
}