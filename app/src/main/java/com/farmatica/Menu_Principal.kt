package com.farmatica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import com.farmatica.databinding.ActivityMenuPrincipalBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Menu_Principal : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMenuPrincipalBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)

        binding.btLogoff.setOnClickListener { haceLogoff() }

        //binding.btPerfilUsuario.setOnClickListener { abrirPerfil() }
    }

    fun haceLogoff() {
        Firebase.auth.signOut()
        finish()
    }

    fun abrirPerfil(view:View){
        val intent = Intent(this,Perfil_Usuario::class.java).apply {  }
        startActivity(intent)
    }

    fun abrirMedicamentos(view:View){
        val intent = Intent(this,Categoria_Producto::class.java).apply {  }
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
}