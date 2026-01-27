package com.example.consumo_de_apis.local

import android.app.Application
import androidx.room.Room

class PersonageApplication: Application() {
    companion object {
        lateinit var dataBase: PersonageDataBase
    }

    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(this, PersonageDataBase::class.java, "PersonageDatabase").build()
    }
}