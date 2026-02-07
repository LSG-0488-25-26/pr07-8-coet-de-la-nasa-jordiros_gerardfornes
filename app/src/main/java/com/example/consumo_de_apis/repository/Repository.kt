package com.example.consumo_de_apis.repository

import com.example.consumo_de_apis.api.ApiInterface
import com.example.consumo_de_apis.local.*
import com.example.consumo_de_apis.model.Personage
import kotlinx.coroutines.flow.Flow

class Repository {
    // ESTABLECER INTERFAZ DE API
    private val apiInterface = ApiInterface.create()
    val daoInterface = PersonageApplication.dataBase.personageDao()

    suspend fun getCharacters(pagina: Int = 1) = apiInterface.getCharacters(pagina) // CARGAR PAGINA 1 DE PERSONAGES POR DEFECTO
    suspend fun getCharacterDetails(id: Int) = apiInterface.getCharacterDetails(id) // CARGAR DETALLES DE PERSONAGES

    fun getFavoritos(): Flow<List<Personage>> = daoInterface.getFavoritos()         // OBTENER LISTADO PEROSNAGE FAVORITOS

    suspend fun setPersonageFavorito(personage: Personage) = daoInterface.setPersonageFavorito(personage) // ESTABLECER PERSONAGE COMO FAVORITO

    suspend fun deletePersonageFavorito(personage: Personage) = daoInterface.deletePersonageFavorito(personage) // ESTABLECER PERSONAGE COMO NO FAVORITO
}