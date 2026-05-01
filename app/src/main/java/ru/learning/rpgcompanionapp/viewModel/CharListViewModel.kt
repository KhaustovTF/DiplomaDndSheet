package ru.learning.rpgcompanionapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.learning.rpgcompanionapp.dto.CharData
import ru.learning.rpgcompanionapp.dto.ReferenceItem
import ru.learning.rpgcompanionapp.repository.CharRepository

class CharListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CharRepository(application)

    val chars = repository.chars
    val skills = repository.skills
    val classes = repository.classes

    init {
        viewModelScope.launch {
            try {
                repository.loadClassesFromServer()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        viewModelScope.launch {
            try {
                repository.loadRacesFromServer()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        viewModelScope.launch {
            try {
                repository.loadSkillsFromServer()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addChar(char: CharData) {
        viewModelScope.launch {
            repository.addChar(char)
        }
    }

    fun debugClassesCount(): Int = repository.getLoadedClasses().size
    fun debugRacesCount(): Int = repository.getLoadedRaces().size
    fun debugSkillsCount(): Int = repository.getLoadedSkills().size
    fun getLoadedClasses(): List<ReferenceItem> = repository.getLoadedClasses()
    fun getLoadedRaces(): List<ReferenceItem> = repository.getLoadedRaces()
    fun getLoadedSkills(): List<ReferenceItem> = repository.getLoadedSkills()
    fun deleteChar(id: Int) {
        viewModelScope.launch {
            repository.deleteChar(id)
        }
    }
}