package com.farmatica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View

class Categoria_Producto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoria_producto)
    }

    fun abrirCuidadoPersonal(view: View){
        val intent = Intent(this,Cuidado_Personal::class.java).apply {  }
        startActivity(intent)
    }
    fun abrirDermatologia(view:View){
        val intent = Intent(this,Dermatologia::class.java).apply {  }
        startActivity(intent)
    }
    fun abrirAnticonseptivosHormonales(view:View){
        val intent = Intent(this,AnticonceptivosHormonales::class.java).apply {  }
        startActivity(intent)
    }
    fun abrirEndocrinologia(view:View){
        val intent = Intent(this,Endocrinologia::class.java).apply {  }
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
}