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
    private var pagina_actual: Int = 1

    var maxPaginas by mutableStateOf("1")
        private set
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
                val response = repository.getCharacters(pagina_actual)
                if (response.isSuccessful) {
                    if (response.body()?.pages != null) {
                        maxPaginas = response.body()?.pages.toString()
                    }
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

    fun ingrementarPagina(pagina: Int): Int {
        if (pagina < maxPaginas.toInt()) {
            pagina_actual = pagina + 1
            loadCharacters()
            return pagina + 1
        }
        else return maxPaginas.toInt()
    }

    fun decrementarPagina(pagina: Int): Int {
        if (pagina > 1) {
            pagina_actual = pagina - 1
            loadCharacters()
            return pagina - 1
        }
        else return 1
    }
}