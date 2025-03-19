package com.o9tech.prankcall.data.apiservices

import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("users")
    suspend fun user():Response<String>
}