package com.example.consumo_de_apis.model
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("results")
    val results: List<Personage>
)