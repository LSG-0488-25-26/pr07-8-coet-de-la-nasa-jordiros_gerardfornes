package com.example.consumo_de_apis.model

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName
import java.util.Date

data class Personage (
    var id: Int,
    var name: String,
    var occupation: String,
    var age: Int,
    var gender: String,
    @SerializedName("birthdate")
    var birthday: String?,
    var status: String,
    var description: String?,
) {
    val imageUrl: String
        get() = "https://cdn.thesimpsonsapi.com/500/character/$id.webp"
}
