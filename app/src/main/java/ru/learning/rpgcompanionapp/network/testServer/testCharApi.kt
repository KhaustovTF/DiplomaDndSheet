package ru.learning.rpgcompanionapp.network.testServer

import ru.learning.rpgcompanionapp.network.ServerCharacterDto

object FakeCharacterApi {

    fun getCharacters(): List<ServerCharacterDto> {
        return listOf(
            ServerCharacterDto(
                id = 1,
                name = "Server Kael",
                race = "Elf",
                className = "Fighter",
                level = 4,
                hp = 30,
                ac = 17,
                speed = 35,
                str = 17,
                dex = 12,
                con = 14,
                intStat = 10,
                wis = 11,
                cha = 9,
                notes = "From server",
                image_path = ""
            ),
            ServerCharacterDto(
                id = 2,
                name = "Server Lyra",
                race = "Human",
                className = "Wizard",
                level = 6,
                hp = 20,
                ac = 12,
                speed = 30,
                str = 8,
                dex = 12,
                con = 13,
                intStat = 19,
                wis = 16,
                cha = 14,
                notes = "Server caster",
                image_path = ""
            )
        )
    }
}