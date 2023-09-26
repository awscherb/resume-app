package com.awscherb.resume.data

import retrofit2.http.GET

interface ResumeApi {

    @GET("res.json")
    suspend fun fetchResume(): ResumeResponse
}