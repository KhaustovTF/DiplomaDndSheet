package ru.learning.rpgcompanionapp.network.AuthReg

data class LoginRequestDto(
    val email: String,
    val password: String
)