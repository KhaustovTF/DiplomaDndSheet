package ru.learning.rpgcompanionapp.repository

import ru.learning.rpgcompanionapp.network.AuthReg.LoginRequestDto
import ru.learning.rpgcompanionapp.network.AuthReg.LoginResponseDto
import ru.learning.rpgcompanionapp.network.RetrofitInstance

class AuthRepository {

    private val authApi = RetrofitInstance.authApi

    suspend fun login(email: String, password: String): LoginResponseDto {
        val request = LoginRequestDto(
            email = email,
            password = password
        )
        return authApi.login(request)
    }
}