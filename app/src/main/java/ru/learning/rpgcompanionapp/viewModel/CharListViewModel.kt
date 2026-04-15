package ru.learning.rpgcompanionapp.viewModel
import androidx.lifecycle.ViewModel
import ru.learning.rpgcompanionapp.dto.CharData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.learning.rpgcompanionapp.R
import ru.learning.rpgcompanionapp.repository.CharRepository

class CharListViewModel : ViewModel() {


    private val repository = CharRepository()
    val chars = repository.chars

    init {
        viewModelScope.launch {
            repository.loadCharsFromFakeServer()
        }
    }

    fun addChar(char: CharData) {
        repository.addChar(char)
    }
}