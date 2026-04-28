package ru.learning.rpgcompanionapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.learning.rpgcompanionapp.BuildConfig
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private const val DND_BASE_URL = "https://www.dnd5eapi.co/api/2014/"

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    val api: CharacterApi by lazy {
        Retrofit.Builder()
            .baseUrl(DND_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterApi::class.java)
    }

    val authApi: AuthApi by lazy {
        Retrofit.Builder()
            .baseUrl(DND_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    val diplomaApi: DiplomaApi by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DiplomaApi::class.java)
    }
}