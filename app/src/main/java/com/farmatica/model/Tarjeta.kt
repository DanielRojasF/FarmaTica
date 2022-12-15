package com.farmatica.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tarjeta(
    @PrimaryKey val idTarjeta: Int,
    val numTarjeta: Int,
    val fechaExp: String,
    val codSeguridad: Int,
)