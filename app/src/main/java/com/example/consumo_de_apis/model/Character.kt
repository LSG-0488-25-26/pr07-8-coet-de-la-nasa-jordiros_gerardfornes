package com.example.consumo_de_apis.model

import androidx.annotation.DrawableRes
import java.util.Date

data class Personage (
    var name: String,
    var occupation: String,
    var gender: String,
    var birthday: Date,
    var status: String,
    var description: String,
    @DrawableRes var image: Int
)