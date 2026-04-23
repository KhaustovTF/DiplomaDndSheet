package ru.learning.rpgcompanionapp.network

import retrofit2.http.GET

interface CharacterApi {

    @GET("classes")
    suspend fun getClasses(): FeatureListResponse

    @GET("races")
    suspend fun getRaces(): FeatureListResponse

    @GET("skills")
    suspend fun getSkills(): FeatureListResponse
}