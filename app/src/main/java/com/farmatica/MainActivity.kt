package com.farmatica
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.farmatica.databinding.IniciarSesionBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth

class MainActivity : AppCompatActivity() {
    //Definimos un objeto para acceder a la autenticacion de firebase
    private lateinit var auth: FirebaseAuth
    //Definimos un objeto para acceder a los elemento de la pantalla xml
    private lateinit var binding: IniciarSesionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = IniciarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth
        setContentView(R.layout.iniciar_sesion)
        //Se define el metodo para hacer el login
        binding.btIniciarSesion.setOnClickListener {
            haceLogin()
        }
        //Se define el metodo para hacer el registro de usuario
        binding.btRegistrarse.setOnClickListener {
            haceRegistro()
        }
    }
    private fun haceRegistro() {
        val email = binding.etEmail.text.toString()
        val clave = binding.etPassword.text.toString()
        //Se hace el registro
        auth.createUserWithEmailAndPassword(email, clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("creando usuario", "Registrado")
                    val user = auth.currentUser
                    actualiza(user)
                } else {
                    Log.d("creando usuario", "Fallo")
                    Toast.makeText(baseContext, "Fallo", Toast.LENGTH_LONG).show()
                    actualiza(null)
                }
            }
    }
    private fun haceLogin() {
        val email = binding.etEmail.text.toString()
        val clave = binding.etPassword.text.toString()
        //Se hace el registro
        auth.signInWithEmailAndPassword(email, clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("Autenticando", "Autenticado")
                    val user = auth.currentUser
                    actualiza(user)
                } else {
                    Log.d("Autenticando", "Fallo")
                    Toast.makeText(baseContext, "Fallo", Toast.LENGTH_LONG).show()
                    actualiza(null)
                }
            }
    }
    private fun actualiza(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    //Esto hara que una vez autenticado.... no pida mas autenticarse a menos que se cierre la sesion
    public override fun onStart() {
        super.onStart()
        val usuario = auth.currentUser
        actualiza(usuario)
    }
}