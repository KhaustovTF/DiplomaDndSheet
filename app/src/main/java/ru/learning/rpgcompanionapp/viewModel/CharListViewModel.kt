package ru.learning.rpgcompanionapp.viewModel
import androidx.lifecycle.ViewModel
import ru.learning.rpgcompanionapp.dto.CharData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.learning.rpgcompanionapp.R

class CharListViewModel : ViewModel() {

    private val _chars = MutableLiveData<List<CharData>>()
    val chars: LiveData<List<CharData>> = _chars


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
        val currentList = _chars.value ?: emptyList()
        val newChar = char.copy(
            charId = generateId()
        )


        _chars.value = currentList + newChar
    }
    private fun generateId(): Int {
        val current = _chars.value ?: emptyList()
        return (current.maxOfOrNull { it.charId } ?: 0) + 1
    }
}