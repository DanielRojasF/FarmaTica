package com.farmatica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Perfil_Usuario  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_usuario)
    }

    fun abrirActualizarUsuario(view: View){
        val intent = Intent(this,Actualizar_Usuario::class.java).apply {  }
        startActivity(intent)
    }
}