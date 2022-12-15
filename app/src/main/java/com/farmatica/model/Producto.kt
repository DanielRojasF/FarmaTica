package com.farmatica.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Producto (
    @PrimaryKey val idProducto: Int,
    val nombreProducto: String,
    val montoProducto: Double,
)