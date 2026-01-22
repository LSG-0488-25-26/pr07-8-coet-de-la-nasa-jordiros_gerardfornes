package com.example.consumo_de_apis.api

import com.example.consumo_de_apis.model.CharacterResponse
import com.example.consumo_de_apis.model.Personage
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("characters")
    suspend fun getCharacters(): Response<CharacterResponse>

    @GET("characters/{id}")
    suspend fun getCharacterDetails(@Path("id") id: Int): Response<Personage>

    companion object {
        const val BASE_URL = "https://thesimpsonsapi.com/api/"

        fun create(): ApiInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}