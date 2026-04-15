package ru.learning.rpgcompanionapp.network
import retrofit2.http.GET

interface CharacterApi {

    @GET("characters")
    suspend fun getCharacters(): List<ServerCharacterDto>

}