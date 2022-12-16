package com.farmatica.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Propietario(
    var idPropetario: String,
    val nombrePropietario: String,
    val direccionPropietario: String,
) : Parcelable{
    constructor():
            this("","","")
}

