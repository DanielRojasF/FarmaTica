package com.farmatica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Carrito_Compras : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito_compras)
    }

    fun abrirMetodoTarjeta(view: View){
        val intent = Intent(this,MetodoTarjeta::class.java).apply {  }
        startActivity(intent)
    }
}