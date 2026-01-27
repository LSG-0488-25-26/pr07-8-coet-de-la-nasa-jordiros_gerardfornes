package com.example.consumo_de_apis.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.consumo_de_apis.model.Personage

@Database(entities = arrayOf(Personage::class), version = 1)
abstract class PersonageDataBase: RoomDatabase() {
    abstract fun personageDao(): PersonageDao
}