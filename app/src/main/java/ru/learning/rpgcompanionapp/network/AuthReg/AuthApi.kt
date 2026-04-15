package ru.learning.rpgcompanionapp.network
import retrofit2.http.Body
import retrofit2.http.POST
import ru.learning.rpgcompanionapp.network.AuthReg.LoginRequestDto
import ru.learning.rpgcompanionapp.network.AuthReg.LoginResponseDto


interface AuthApi {
    @POST("login")
    suspend fun login(@Body request: LoginRequestDto): LoginResponseDto
}