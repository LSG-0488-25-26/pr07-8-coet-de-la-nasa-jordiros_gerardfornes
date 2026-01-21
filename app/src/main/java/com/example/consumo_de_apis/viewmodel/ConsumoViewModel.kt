package com.example.consumo_de_apis.viewmodel

import androidx.lifecycle.ViewModel
import com.example.consumo_de_apis.R
import com.example.consumo_de_apis.model.Personage
import java.text.SimpleDateFormat
import java.util.Locale

class ConsumoViewModel: ViewModel() {
    fun getCharacterList(): MutableList<Personage> {
        val listCharacters: MutableList<Personage> = mutableListOf()
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        listCharacters.add(Personage(1, "Homer Simpson", "Safety Inspector", 39, "Male", formatter.parse("1956-05-12")!!, "Alive", "blablabla", R.drawable.homersimpson))
        listCharacters.add(Personage(2, "Marge Simpson", "Unemployed", 39, "Female", null, "Alive", "blablabla", R.drawable.margesimpson))
        listCharacters.add(Personage(3, "Bart Simpson", "Student at Springfield Elementary School", 10, "Male", formatter.parse("1980-02-23")!!, "Alive", "blablabla", R.drawable.bartsimpson))
        listCharacters.add(Personage(4, "Lisa Simpson", "Student at Springfield Elementary School", 8, "Female", formatter.parse("1982-05-09")!!, "Alive", "blablabla", R.drawable.lisasimpson))
        listCharacters.add(Personage(5, "Maggie Simpson", "Unknown", 1, "Female", formatter.parse("1990-11-07")!!, "Alive", "blablabla", R.drawable.maggiesimpson))
        listCharacters.add(Personage(1, "Homer Simpson", "Safety Inspector", 39, "Male", formatter.parse("1956-05-12")!!, "Alive", "blablabla", R.drawable.homersimpson))
        listCharacters.add(Personage(2, "Marge Simpson", "Unemployed", 39, "Female", null, "Alive", "blablabla", R.drawable.margesimpson))
        listCharacters.add(Personage(3, "Bart Simpson", "Student at Springfield Elementary School", 10, "Male", formatter.parse("1980-02-23")!!, "Alive", "blablabla", R.drawable.bartsimpson))
        listCharacters.add(Personage(4, "Lisa Simpson", "Student at Springfield Elementary School", 8, "Female", formatter.parse("1982-05-09")!!, "Alive", "blablabla", R.drawable.lisasimpson))
        listCharacters.add(Personage(5, "Maggie Simpson", "Unknown", 1, "Female", formatter.parse("1990-11-07")!!, "Alive", "blablabla", R.drawable.maggiesimpson))
        listCharacters.add(Personage(1, "Homer Simpson", "Safety Inspector", 39, "Male", formatter.parse("1956-05-12")!!, "Alive", "blablabla", R.drawable.homersimpson))
        listCharacters.add(Personage(2, "Marge Simpson", "Unemployed", 39, "Female", null, "Alive", "blablabla", R.drawable.margesimpson))
        listCharacters.add(Personage(3, "Bart Simpson", "Student at Springfield Elementary School", 10, "Male", formatter.parse("1980-02-23")!!, "Alive", "blablabla", R.drawable.bartsimpson))
        listCharacters.add(Personage(4, "Lisa Simpson", "Student at Springfield Elementary School", 8, "Female", formatter.parse("1982-05-09")!!, "Alive", "blablabla", R.drawable.lisasimpson))
        listCharacters.add(Personage(5, "Maggie Simpson", "Unknown", 1, "Female", formatter.parse("1990-11-07")!!, "Alive", "blablabla", R.drawable.maggiesimpson))

        return listCharacters
    }

    fun getCharacterById(id: Int): Personage? {
        return getCharacterList().find { it.id == id }
    }
}
