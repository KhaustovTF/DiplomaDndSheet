package ru.learning.rpgcompanionapp.repository

import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import ru.learning.rpgcompanionapp.dto.CharData
import ru.learning.rpgcompanionapp.dto.ReferenceItem
import ru.learning.rpgcompanionapp.mapper.toCharData
import ru.learning.rpgcompanionapp.mapper.toEntity
import ru.learning.rpgcompanionapp.network.RetrofitInstance

class CharRepository(context: Context) {

    private val database = ru.learning.rpgcompanionapp.data.local.AppDatabase.getDatabase(context)
    private val characterDao = database.characterDao()
    private val _races = MutableStateFlow<List<ReferenceItem>>(emptyList())
    val races: StateFlow<List<ReferenceItem>> = _races

    private val _skills = MutableStateFlow<List<ReferenceItem>>(emptyList())
    val skills: StateFlow<List<ReferenceItem>> = _skills
    private val api = RetrofitInstance.api
    val chars = characterDao.getAllCharacters().map { list ->
        list.map { it.toCharData() }
    }

    private val _classes = MutableStateFlow<List<ReferenceItem>>(emptyList())
    val classes: StateFlow<List<ReferenceItem>> = _classes

    fun getLoadedClasses(): List<ReferenceItem> = _classes.value
    fun getLoadedRaces(): List<ReferenceItem> = _races.value
    fun getLoadedSkills(): List<ReferenceItem> = _skills.value





//    }

    //    init {
//        _chars.value = listOf(
//            CharData(
//                charId = 1,
//                charName = "Kael",
//                charRace = "Elf",
//                charLevel = 3,
//                charClass = "Fighter",
//                charHp = 28,
//                charAc = 16,
//                charSpeed = 35,
//                charStr = 16,
//                charDex = 12,
//                charCon = 14,
//                charInt = 9,
//                charWis = 11,
//                charCha = 8,
//                charNotes = "Frontline fighter",
//                charImage = (R.drawable.ic_launcher_foreground).toString()
//            ),
//            CharData(
//                charId = 2,
//                charName = "Lyra",
//                charRace = "Human",
//                charLevel = 5,
//                charClass = "Wizzard",
//                charHp = 18,
//                charAc = 11,
//                charSpeed = 30,
//                charStr = 8,
//                charDex = 11,
//                charCon = 13,
//                charInt = 18,
//                charWis = 16,
//                charCha = 14,
//                charNotes = "Backtline Caster",
//                charImage = (R.drawable.ic_launcher_foreground).toString()
//            )
//        )
//
    suspend fun addChar(char: CharData) {
        characterDao.insertCharacter(char.toEntity())
    }
//
//    private fun generateId(): Int {
//        val current = _chars.value
//        return (current.maxOfOrNull { it.charId } ?: 0) + 1
//    }

    suspend fun getCharById(id: Int): CharData? {
        return characterDao.getCharacterById(id)?.toCharData()
    }

//    fun setCharsFromServer(dtos: List<ServerCharacterDto>){
//        _chars.value = dtos.map {it.toCharData() }
//    }

//    fun loadCharsFromFakeServer() {
//        println("Подделка/пустышка")
//        setCharsFromServer(FakeCharacterApi.getCharacters())
//    }

    suspend fun loadClassesFromServer() {
        val response = api.getClasses()
        val dtos = response.results

        _classes.value = dtos.map {
            ReferenceItem(
                index = it.index,
                name = it.name,
                url = it.url
            )
        }
        Log.d("RPG_DEBUG", "loaded classes = ${_classes.value.size}")
    }

    suspend fun loadRacesFromServer() {
        val response = api.getRaces()
        val dtos = response.results

        _races.value = dtos.map {
            ReferenceItem(
                index = it.index,
                name = it.name,
                url = it.url
            )
        }
        Log.d("RPG_DEBUG", "loaded races = ${_races.value.size}")
    }

    suspend fun loadSkillsFromServer() {
        val response = api.getSkills()
        val dtos = response.results

        _skills.value = dtos.map {
            ReferenceItem(
                index = it.index,
                name = it.name,
                url = it.url
            )
        }

        Log.d("RPG_DEBUG", "loaded skills = ${_skills.value.size}")
    }

    suspend fun deleteChar(id: Int) {
        characterDao.deleteById(id)
    }

}