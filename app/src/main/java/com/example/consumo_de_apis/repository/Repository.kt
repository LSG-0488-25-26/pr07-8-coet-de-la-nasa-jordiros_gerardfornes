package com.example.consumo_de_apis.repository

import com.example.consumo_de_apis.api.ApiInterface
import com.example.consumo_de_apis.local.*
import com.example.consumo_de_apis.model.Personage
import kotlinx.coroutines.flow.Flow

class Repository {
    private val apiInterface = ApiInterface.create()
    val daoInterface = PersonageApplication.dataBase.personageDao()

    suspend fun getCharacters(pagina: Int = 1) = apiInterface.getCharacters(pagina)
    suspend fun getCharacterDetails(id: Int) = apiInterface.getCharacterDetails(id)

    fun getFavoritos(): Flow<List<Personage>> = daoInterface.getFavoritos()

    suspend fun setPersonageFavorito(personage: Personage) = daoInterface.setPersonageFavorito(personage)

    suspend fun deletePersonageFavorito(personage: Personage) = daoInterface.deletePersonageFavorito(personage)
}