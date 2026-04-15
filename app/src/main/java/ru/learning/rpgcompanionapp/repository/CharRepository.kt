package ru.learning.rpgcompanionapp.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.learning.rpgcompanionapp.R
import ru.learning.rpgcompanionapp.dto.CharData
import ru.learning.rpgcompanionapp.mapper.toCharData
import ru.learning.rpgcompanionapp.network.ServerCharacterDto
import ru.learning.rpgcompanionapp.network.testServer.FakeCharacterApi
import ru.learning.rpgcompanionapp.network.RetrofitInstance

class CharRepository {

    private val api = RetrofitInstance.api
    private val _chars = MutableStateFlow<List<CharData>>(emptyList())
    val chars: StateFlow<List<CharData>> = _chars


    fun getCharById(id: Int): CharData? {
        return _chars.value.find { it.charId == id }
    }



    init {
        _chars.value = listOf(
            CharData(
                charId = 1,
                charName = "Kael",
                charRace = "Elf",
                charLevel = 3,
                charClass = "Fighter",
                charHp = 28,
                charAc = 16,
                charSpeed = 35,
                charStr = 16,
                charDex = 12,
                charCon = 14,
                charInt = 9,
                charWis = 11,
                charCha = 8,
                charNotes = "Frontline fighter",
                charImage = (R.drawable.ic_launcher_foreground).toString()
            ),
            CharData(
                charId = 2,
                charName = "Lyra",
                charRace = "Human",
                charLevel = 5,
                charClass = "Wizzard",
                charHp = 18,
                charAc = 11,
                charSpeed = 30,
                charStr = 8,
                charDex = 11,
                charCon = 13,
                charInt = 18,
                charWis = 16,
                charCha = 14,
                charNotes = "Backtline Caster",
                charImage = (R.drawable.ic_launcher_foreground).toString()
            )
        )

    }

    fun addChar(char: CharData) {
        val newChar = char.copy(
            charId = generateId()
        )
        _chars.value = _chars.value + newChar
    }

    private fun generateId(): Int {
        val current = _chars.value
        return (current.maxOfOrNull { it.charId } ?: 0) + 1
    }

    fun setCharsFromServer(dtos: List<ServerCharacterDto>){
        _chars.value = dtos.map {it.toCharData() }
    }

    fun loadCharsFromFakeServer() {
        println("Подделка/пустышка")
        setCharsFromServer(FakeCharacterApi.getCharacters())
    }

    suspend fun loadCharsFromServer() {
        println("Real boyS")
        val dtos = api.getCharacters()
        setCharsFromServer(dtos)
    }

}