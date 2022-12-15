package com.farmatica.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="propetario")
data class Propetario(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name="nombre")
    val nombre: String,
    @ColumnInfo(name="idTARJETA")
    val latitud: Double?,


) : Parcelable
