package com.farmatica.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.farmatica.model.Propetario
import com.farmatica.model.TarjetasXPropietario

@Dao
interface PropetarioDao {
    //CRUD Create Read Update Delete
    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    //suspend fun addLugar(propetario: Propetario)
    //@Update(onConflict = OnConflictStrategy.IGNORE)
    //suspend fun updateLugar(propetario: Propetario)
    //@Delete
    //suspend fun deleteLugar(propetario: Propetario)
    //@Query("SELECT * FROM Propetario")
    //fun getLugares() : LiveData<List<Propetario>>

    @Transaction
    @Query("SELECT * FROM Propetario")
    fun getTarjetasXPropietario(): List<TarjetasXPropietario>


}