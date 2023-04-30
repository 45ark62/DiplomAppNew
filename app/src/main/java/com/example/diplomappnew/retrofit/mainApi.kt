package com.example.diplomappnew.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface mainApi {
    @POST("/api/User/login")
    suspend fun auth(@Body auth: AuthRequest): Response<User>
    @Headers(
        "Content-Type: application/json",
         "accept: text/plain"
    )
    @GET("/api/Account")
    suspend fun getUserAccount(@Header("Authorization") accessToken:String):Account
    @POST("/api/User/register")
    suspend fun register(@Body register: Register):Response<UserR>

}

