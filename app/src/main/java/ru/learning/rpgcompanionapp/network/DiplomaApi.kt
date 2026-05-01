package ru.learning.rpgcompanionapp.network

import retrofit2.Response
import retrofit2.http.GET
import ru.learning.rpgcompanionapp.BuildConfig

interface DiplomaApi {

    @GET("api/v1/characters")
    suspend fun ping(
        @retrofit2.http.Query("key") key: String = BuildConfig.API_KEY
    ): Response<Any>
}