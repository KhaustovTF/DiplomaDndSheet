package ru.learning.rpgcompanionapp.mapper

import ru.learning.rpgcompanionapp.dto.CharData
import ru.learning.rpgcompanionapp.dto.local.CharacterEntity

fun CharacterEntity.toCharData(): CharData {
    return CharData(
        charId = id,
        charName = name,
        charRace = race,
        charLevel = level,
        charClass = charClass,
        charHp = hp,
        charAc = ac,
        charSpeed = speed,
        charStr = str,
        charDex = dex,
        charCon = con,
        charInt = intStat,
        charWis = wis,
        charCha = cha,
        charNotes = "",
        charImage = "",
        skills = if (skillsJson.isBlank()) emptyList() else skillsJson.split("|")
    )
}

fun CharData.toEntity(): CharacterEntity {
    return CharacterEntity(
        id = if (charId > 0) charId else 0,
        name = charName,
        race = charRace,
        charClass = charClass,
        level = charLevel,
        hp = charHp,
        ac = charAc,
        speed = charSpeed,
        str = charStr,
        dex = charDex,
        con = charCon,
        intStat = charInt,
        wis = charWis,
        cha = charCha,
        skillsJson = skills.joinToString("|")
    )
}