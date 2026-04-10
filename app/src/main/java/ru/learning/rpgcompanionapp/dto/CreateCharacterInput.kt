package ru.learning.rpgcompanionapp.dto

data class CreateCharacterInput(
    val name: String,
    val race: String,
    val className: String,
    val level: Int,
    val hp: Int,
    val ac: Int,
    val speed: Int,
    val str: Int,
    val dex: Int,
    val con: Int,
    val intStat: Int,
    val wis: Int,
    val cha: Int
)