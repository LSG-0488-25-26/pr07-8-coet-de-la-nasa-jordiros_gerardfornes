package com.example.consumo_de_apis.model

import com.google.gson.annotations.SerializedName
import androidx.room. Entity
import androidx.room. PrimaryKey

@Entity(tableName = "personages")
data class Personage (
    @PrimaryKey var id: Int,
    var name: String,
    var occupation: String,
    var age: Int,
    var gender: String,
    @SerializedName("birthdate")
    var birthday: String?,
    var status: String,
    var description: String?,
    var esFavorito: Boolean = false
) {
    val imageUrl: String
        get() = "https://cdn.thesimpsonsapi.com/500/character/$id.webp"
}