package com.farmatica.repository

import androidx.lifecycle.LiveData
import com.farmatica.data.FacturaDao
import com.farmatica.model.Factura


class FacturaRepository (private val facturaDao: FacturaDao){

    fun saveFactura(factura: Factura){
        facturaDao.saveFactura(factura)
    }


    val getFacturas : LiveData<List<Factura>> = facturaDao.getFacturas()
}