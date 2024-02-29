package com.awscherb.resume.data

import retrofit2.http.GET

interface ProjectsApi {

    @GET("projects.json")
    suspend fun fetchProjects(): ProjectsResponse
}