package com.roydev.tastytrends

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("testers/register") // Replace with your endpoint
    suspend fun Register(@Body request: RegisterReq): RegisterRes

    @POST("testers/login") // Example for a parameterized endpoint
    suspend fun Login(@Body request: LoginReq): LoginRes
}
