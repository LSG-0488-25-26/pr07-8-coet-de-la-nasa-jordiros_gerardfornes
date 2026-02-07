package com.example.consumo_de_apis.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.consumo_de_apis.model.Personage
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonageDao {
    // OBTENER PERSONAGES QUE SEAN FAVORITOS
    @Query("SELECT * FROM personages WHERE esFavorito = 1")
    fun getFavoritos(): Flow<List<Personage>>

    // GUARDAR PERSONAGE A FAVORITOS
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setPersonageFavorito(personage: Personage)

    // ELIMINAR PERSONAGE DE FAVORITOS
    @Delete
    suspend fun deletePersonageFavorito(personage: Personage)
}