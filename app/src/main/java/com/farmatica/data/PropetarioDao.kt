package com.farmatica.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.farmatica.model.Propetario

@Dao
interface PropetarioDao {
    //CRUD Create Read Update Delete
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLugar(propetario: Propetario)
    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateLugar(propetario: Propetario)
    @Delete
    suspend fun deleteLugar(propetario: Propetario)
    @Query("SELECT * FROM Propetario")
    fun getLugares() : LiveData<List<Propetario>>
}