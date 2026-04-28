package ru.learning.rpgcompanionapp.dto.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val race: String,
    val charClass: String,
    val level: Int,

    val hp: Int,
    val ac: Int,
    val speed: Int,

    val str: Int,
    val dex: Int,
    val con: Int,
    val intStat: Int,
    val wis: Int,
    val cha: Int,

    val skillsJson: String,
    val charImage: String
)