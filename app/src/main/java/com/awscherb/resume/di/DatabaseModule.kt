package com.awscherb.resume.di

import android.content.Context
import androidx.room.Room
import com.awscherb.resume.data.ResumeDao
import com.awscherb.resume.data.ResumeDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ResumeDb {
        return Room.databaseBuilder(
            context,
            ResumeDb::class.java,
            "resume-1.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideResumeDao(db: ResumeDb): ResumeDao = db.resumeDao()

}