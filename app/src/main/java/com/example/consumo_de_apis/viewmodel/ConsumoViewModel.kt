package com.example.consumo_de_apis.viewmodel

import android.graphics.Color
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consumo_de_apis.model.Personage
import com.example.consumo_de_apis.nav.BottomNavigationScreens
import com.example.consumo_de_apis.repository.Repository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ConsumoViewModel : ViewModel {

    // ELEMENTOS DEL BOTTOM NAVIGATION
    private val _bottomNavigationItems =
        MutableLiveData<List<BottomNavigationScreens>>(emptyList())
    val bottomNavigationItems: LiveData<List<BottomNavigationScreens>> =
        _bottomNavigationItems

    // CONSTRUCTOR QUE INICIALIZA LOS ITEMS DEL BOTTOM NAVIGATION
    constructor() : super() {
        _bottomNavigationItems.value = listOf(
            BottomNavigationScreens.Main,
            BottomNavigationScreens.Favorites
        )
    }

    private val repository = Repository() // REPOSITORIO QUE CONECTA API Y BASE DE DATOS

    private var pagina_actual: Int = 1 // PAGINA ACTUAL DE LA API

    // LISTA DE PERSONAJES FAVORITOS DESDE ROOM
    val favoritos = repository.getFavoritos()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    var maxPaginas by mutableStateOf("1") // NUMERO MAXIMO DE PAGINAS DISPONIBLES EN LA API

    // LISTA DE PERSONAJES CARGADOS DESDE LA API
    private val _characters = mutableStateListOf<Personage>()
    val characters: List<Personage> = _characters

    // PERSONAJE SELECCIONADO PARA LA VISTA DE DETALLES
    var personageDetail by mutableStateOf<Personage?>(null)
        private set

    // CARGA INICIAL DE PERSONAJES
    init {
        loadCharacters()
    }

    // CARGA PERSONAJES DESDE LA API SEGUN LA PAGINA ACTUAL
    private fun loadCharacters() {
        viewModelScope.launch {
            try {
                val response = repository.getCharacters(pagina_actual)
                if (response.isSuccessful) {
                    response.body()?.pages?.let {
                        maxPaginas = it.toString()
                    }
                    response.body()?.results?.let { results ->
                        _characters.clear()
                        _characters.addAll(results)
                    }
                } else {
                    Log.e("API", "ERROR: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("API", "ERROR CONEXION: ${e.message}")
            }
        }
    }

    // CARGA LOS DETALLES DE UN PERSONAJE CONCRETO
    fun fetchCharacterDetails(id: Int) {
        personageDetail = null
        viewModelScope.launch {
            try {
                val response = repository.getCharacterDetails(id)
                if (response.isSuccessful) {
                    personageDetail = response.body()
                }
            } catch (e: Exception) {
                Log.e("API", "ERROR DETALLE: ${e.message}")
            }
        }
    }

    // DEVUELVE EL COLOR SEGUN EL ESTADO DEL PERSONAJE
    fun getStatusColor(status: String): Int {
        return if (status == "Alive") Color.GREEN else Color.RED
    }

    // AVANZA A LA SIGUIENTE PAGINA
    fun ingrementarPagina(pagina: Int): Int {
        return if (pagina < maxPaginas.toInt()) {
            pagina_actual = pagina + 1
            loadCharacters()
            pagina_actual
        } else pagina_actual
    }

    // RETROCEDE A LA PAGINA ANTERIOR
    fun decrementarPagina(pagina: Int): Int {
        return if (pagina > 1) {
            pagina_actual = pagina - 1
            loadCharacters()
            pagina_actual
        } else pagina_actual
    }

    // VA A LA ULTIMA PAGINA
    fun irAUltimaPagina(): Int {
        pagina_actual = maxPaginas.toInt()
        loadCharacters()
        return pagina_actual
    }

    // VA A LA PRIMERA PAGINA
    fun irAPrimeraPagina(): Int {
        pagina_actual = 1
        loadCharacters()
        return pagina_actual
    }

    // AÑADE O ELIMINA UN PERSONAJE DE FAVORITOS Y ACTUALIZA LA UI
    fun cambiarFavorito(personage: Personage) {
        viewModelScope.launch {
            val actualizado = personage.copy(
                esFavorito = !personage.esFavorito
            )

            if (actualizado.esFavorito) {
                repository.setPersonageFavorito(actualizado)
            } else {
                repository.deletePersonageFavorito(personage)
            }

            // ACTUALIZA EL ESTADO EN LA LISTA PARA FORZAR RECOMPOSICION
            val index = _characters.indexOfFirst { it.id == personage.id }
            if (index != -1) {
                _characters[index] = actualizado
            }
        }
    }
}
