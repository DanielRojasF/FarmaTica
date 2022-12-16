package com.farmatica.repository

import androidx.lifecycle.LiveData
import com.farmatica.data.PropietarioDao
import com.farmatica.model.Propietario

class PropietarioRepository (private val propietarioDao: PropietarioDao){
    fun savePropietario(propietario: Propietario){
        propietarioDao.savePropietario(propietario)
    }

    val getPropietario : LiveData<List<Propietario>> = propietarioDao.getPropietario()
}