package com.example.matchmate.data.api

import com.example.matchmate.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    suspend fun getMatches(
        @Query("results") results: Int = 10
    ): Response<ApiResponse>
}
