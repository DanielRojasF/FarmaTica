package com.farmatica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Contactos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactos)
    }

    fun abrirSoporteTecnico(view: View){
        val intent = Intent(this,Soporte_Tecnico::class.java).apply {  }
        startActivity(intent)
    }
    fun abrirServicioAlCliente(view: View){
        val intent = Intent(this,Servicio_Cliente::class.java).apply {  }
        startActivity(intent)
    }
    fun abrirFAQ(view: View){
        val intent = Intent(this,Faq::class.java).apply {  }
        startActivity(intent)
    }
    fun abrirRedesSociales(view: View){
        val intent = Intent(this,Nuestras_Redes::class.java).apply {  }
        startActivity(intent)
    }
}