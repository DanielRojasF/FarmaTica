package com.farmatica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Menu_Principal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
    }

    fun haceLogoff() {
        Firebase.auth.signOut()
        finish()
    }

    fun abrirCarritoCompras(view: View){
        val intent = Intent(this,Carrito_Compras::class.java).apply {  }
        startActivity(intent)
    }

    fun abrirMedicamentos(view:View){
        val intent = Intent(this,Categoria_Producto::class.java).apply {  }
        startActivity(intent)
    }

    fun abrirContactos(view:View){
        val intent = Intent(this,Contactos::class.java).apply {  }
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
}