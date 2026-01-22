package com.example.consumo_de_apis.viewmodel

import android.graphics.Color
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf // <--- AFEGIT
import androidx.compose.runtime.setValue     // <--- AFEGIT
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consumo_de_apis.model.Personage
import com.example.consumo_de_apis.repository.Repository
import kotlinx.coroutines.launch
import com.example.consumo_de_apis.model.CharacterResponse
class ConsumoViewModel: ViewModel() {
    private val repository = Repository()

    private val _characters = mutableStateListOf<Personage>()
    val characters: List<Personage> = _characters

    var personageDetail by mutableStateOf<Personage?>(null)
        private set

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            try {
                val response = repository.getCharacters()
                if (response.isSuccessful) {
                    val results = response.body()?.results
                    if (results != null) {
                        _characters.clear()
                        _characters.addAll(results)
                    }
                } else {
                    Log.e("API", "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Error connexió: ${e.message}")
            }
        }
    }

    fun fetchCharacterDetails(id: Int) {
        personageDetail = null
        viewModelScope.launch {
            try {
                val response = repository.getCharacterDetails(id)
                if (response.isSuccessful) {
                    personageDetail = response.body()
                }
            } catch (e: Exception) {
                Log.e("API", "Error detall: ${e.message}")
            }
        }
    }

    fun getCharacterById(id: Int): Personage? {
        return _characters.find { it.id == id }
    }

    fun getStatusColor(status: String): Int {
        return if (status == "Alive") Color.GREEN else Color.RED
    }
}