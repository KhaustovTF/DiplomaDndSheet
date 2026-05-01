package ru.learning.rpgcompanionapp.repository

import android.content.Context
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

    suspend fun addChar(char: CharData) {
        characterDao.insertCharacter(char.toEntity())
    }


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

    }

    suspend fun deleteChar(id: Int) {
        characterDao.deleteById(id)
    }

}