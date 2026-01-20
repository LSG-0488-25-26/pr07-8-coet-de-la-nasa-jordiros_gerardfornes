package com.example.consumo_de_apis.viewmodel

import com.example.consumo_de_apis.R
import com.example.consumo_de_apis.model.Personage
import java.text.SimpleDateFormat
import java.util.Locale

fun getCharacterList(): MutableList<Personage> {
    val listCharacters: MutableList<Personage> = mutableListOf()
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    listCharacters.add(Personage("Homer Simpson", "Safety Inspector", 39, "Male", formatter.parse("1956-05-12")!!, "Alive", "blablabla", R.drawable.homersimpson))
    listCharacters.add(Personage("Marge Simpson", "Unemployed", 39, "Female", null, "Alive", "blablabla", R.drawable.margesimpson))
    listCharacters.add(Personage("Bart Simpson", "Student at Springfield Elementary School", 10, "Male", formatter.parse("1980-02-23")!!, "Alive", "blablabla", R.drawable.bartsimpson))
    listCharacters.add(Personage("Lisa Simpson", "Student at Springfield Elementary School", 8, "Female", formatter.parse("1982-05-09")!!, "Alive", "blablabla", R.drawable.lisasimpson))
    listCharacters.add(Personage("Maggie Simpson", "Unknown", 1, "Female", formatter.parse("1990-11-07")!!, "Alive", "blablabla", R.drawable.maggiesimpson))

    return listCharacters
}