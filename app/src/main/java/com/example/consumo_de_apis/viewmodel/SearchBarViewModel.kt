package com.example.consumo_de_apis.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchBarViewModel : ViewModel() {

    // TEXTO ACTUAL INTRODUCIDO EN LA SEARCH BAR
    private val _searchedText = MutableLiveData("")
    val searchedText: LiveData<String> = _searchedText

    // HISTORIAL DE BUSQUEDAS REALIZADAS
    private val _searchHistory = MutableLiveData<List<String>>(emptyList())
    val searchHistory: LiveData<List<String>> = _searchHistory

    // ACTUALIZA EL TEXTO DE BUSQUEDA CUANDO EL USUARIO ESCRIBE
    fun onSearchTextChange(text: String) {
        _searchedText.value = text
    }

    // AÑADE EL TEXTO AL HISTORIAL DE BUSQUEDAS
    fun addToHistory(text: String) {
        if (text.isNotBlank()) {
            val currentHistory = _searchHistory.value.orEmpty()
            _searchHistory.value = listOf(text) + currentHistory
            _searchedText.value = ""
        }
    }

    // BORRA COMPLETAMENTE EL HISTORIAL DE BUSQUEDAS
    fun clearHistory() {
        _searchHistory.value = emptyList()
    }
}
