package com.farmatica.model
import androidx.room.*
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class Factura (
    @PrimaryKey val idFactura: Int,
    val montoFactura: Double,
)

@Entity
data class FacturasXUsuario(
    @Embedded val propetario: Propetario,
    @Relation(
        parentColumn = "idPropietario",
        entityColumn = "idFactura",
    )
    val facturas: List<Factura>
)

@Entity
data class ProductosXFactura(
    @Embedded val factura: Factura,
    @Relation(
        parentColumn = "idFactura",
        entityColumn = "idProducto",
    )
    val productos: List<Producto>
)