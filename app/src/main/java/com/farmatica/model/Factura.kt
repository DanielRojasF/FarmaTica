package com.farmatica.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Factura (
    var idFactura: String,
    val montoFactura: Double,
): Parcelable {
    constructor():
            this("",0.0)
}
