package ru.learning.rpgcompanionapp.dto

data class CharData(
    val charId: Int,
    val charName: String,
    val charRace: String,
    val charLevel: Int,
    val charClass: String,
    val charHp: Int,
    val charAc: Int,
    val charSpeed: Int,
    val charStr: Int,
    val charDex: Int,
    val charCon: Int,
    val charInt: Int,
    val charWis: Int,
    val charCha: Int,
    val charNotes: String,
    val charImage: String,
    val skills: List<String> = emptyList()

)