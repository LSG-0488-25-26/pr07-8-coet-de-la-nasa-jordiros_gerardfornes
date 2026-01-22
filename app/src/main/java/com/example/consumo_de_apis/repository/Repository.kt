package com.example.consumo_de_apis.repository

import com.example.consumo_de_apis.api.ApiInterface

class Repository {
    private val apiInterface = ApiInterface.create()

    suspend fun getCharacters() = apiInterface.getCharacters()

    suspend fun getCharacterDetails(id: Int) = apiInterface.getCharacterDetails(id)
}