package ru.learning.rpgcompanionapp.mapper

import ru.learning.rpgcompanionapp.dto.CharData
import ru.learning.rpgcompanionapp.network.ServerCharacterDto

fun ServerCharacterDto.toCharData(): CharData {
    return CharData(
        charId = id,
        charName = name,
        charRace = race ?: "Unknown",
        charLevel = level,
        charClass = className ?: "Unknown",
        charHp = hp ?: 0,
        charAc = ac ?: 0,
        charSpeed = speed,
        charStr = str ?: 10,
        charDex = dex ?: 10,
        charCon = con ?: 10,
        charInt = intStat ?: 10,
        charWis = wis ?: 10,
        charCha = cha ?: 10,
        charNotes = notes ?: "",
        charImage = image_path ?: ""
    )
}