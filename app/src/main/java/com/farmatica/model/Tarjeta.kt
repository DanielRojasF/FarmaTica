package com.farmatica.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tarjeta(
    var idTarjeta: String,
    val numTarjeta: Int,
    val fechaExp: String,
    val codSeguridad: Int,
) : Parcelable{
    constructor():
            this("",0,"",0)
}