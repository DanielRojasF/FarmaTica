package com.lugares.repository

import androidx.lifecycle.LiveData
import com.farmatica.data.PropetarioDao
import com.farmatica.model.Propetario

class TarjetaPropetarioRepository(private val propetarioDao: PropetarioDao) {
    suspend fun saveLugar(propetario: Propetario) {
        if (propetario.id==0) {  //Es un lugar nuevo
            propetarioDao.addLugar(propetario)
        } else {  //El lugar existe... entonces se actualiza...
            propetarioDao.updateLugar(propetario)
        }
    }
    suspend fun deleteLugar(lugar: Propetario) {
        propetarioDao.deleteLugar(lugar)
    }
    val getLugares : LiveData<List<Propetario>> =propetarioDao.getLugares()
}