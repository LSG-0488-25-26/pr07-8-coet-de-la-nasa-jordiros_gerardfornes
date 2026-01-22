package com.example.consumo_de_apis.repository

import com.example.consumo_de_apis.api.ApiInterface

class Repository {
    private val apiInterface = ApiInterface.create()

    suspend fun getCharacters(pagina: Int = 1) = apiInterface.getCharacters(pagina)
    suspend fun getCharacterDetails(id: Int) = apiInterface.getCharacterDetails(id)
}