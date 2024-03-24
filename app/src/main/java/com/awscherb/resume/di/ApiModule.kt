package com.awscherb.resume.di

import com.awscherb.resume.data.ProjectsApi
import com.awscherb.resume.data.ResumeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    fun provideOkHttp() =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.HEADERS)
            )
            .build()

    @Provides
    fun provideRetrofit(okHttp: OkHttpClient) =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .baseUrl("https://www.awscherb.com/")
            .build()

    @Provides
    @Singleton
    fun provideResumeApi(retrofit: Retrofit): ResumeApi =
        retrofit.create<ResumeApi>()

    @Provides
    @Singleton
    fun provideProjectsApi(retrofit: Retrofit): ProjectsApi =
        retrofit.create<ProjectsApi>()

}