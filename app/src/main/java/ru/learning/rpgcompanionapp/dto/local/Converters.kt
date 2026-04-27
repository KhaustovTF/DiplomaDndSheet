package ru.learning.rpgcompanionapp.dto.local

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromSkills(skills: List<String>): String {
        return skills.joinToString("|")
    }

    @TypeConverter
    fun toSkills(value: String): List<String> {
        if (value.isBlank()) return emptyList()
        return value.split("|")
    }
}