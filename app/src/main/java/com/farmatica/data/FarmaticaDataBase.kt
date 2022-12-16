package com.farmatica.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.farmatica.model.Propietario

@Database(entities=[Propietario::class], version = 1, exportSchema = false)
abstract class FarmaticaDataBase : RoomDatabase(){
    abstract fun farmaticaDao() : TarjetaDao

    companion object {
        @Volatile
        private var INSTANCE: FarmaticaDataBase? = null
        fun getDatabase(context: android.content.Context) : FarmaticaDataBase {
            val temp = INSTANCE
            if (temp!=null) {
                return temp
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FarmaticaDataBase::class.java,
                    "FarmaticaDataBase"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}