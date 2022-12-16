package com.farmatica.repository

import androidx.lifecycle.LiveData
import com.farmatica.data.TarjetaDao
import com.farmatica.model.Tarjeta

class TarjetaRpository (private val tarjetaDao: TarjetaDao){
    fun saveTarjeta(tarjeta: Tarjeta){
        tarjetaDao.saveTarjeta(tarjeta)
    }

    fun deleteTarjeta(tarjeta: Tarjeta){
        tarjetaDao.deleteTarjeta(tarjeta)
    }

    val getTarjetas : LiveData<List<Tarjeta>> = tarjetaDao.getTarjetas()
}