package ru.learning.rpgcompanionapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.learning.rpgcompanionapp.repository.AuthRepository
import android.util.Log

class AuthViewModel : ViewModel() {

    private val repository = AuthRepository()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(email, password)
                Log.d("AUTH_TEST", "TOKEN: ${response.token}")
            } catch (e: Exception) {
                Log.e("AUTH_TEST", "ERROR: ${e.message}", e)
            }
        }
    }
}