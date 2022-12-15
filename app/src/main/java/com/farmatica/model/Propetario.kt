package com.farmatica.model
import androidx.room.*


@Entity
data class Propetario(
    @PrimaryKey val idPropetario: Int,
    val nombrePropietario: String,
    val direccionPropietario: String,
)

@Entity
data class TarjetasXPropietario(
    @Embedded val propetario: Propetario,
    @Relation(
            parentColumn = "idPropietario",
            entityColumn = "idTarjeta",
    )
    val tarjetas: List<Tarjeta>
)
