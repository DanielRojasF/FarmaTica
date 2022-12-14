package com.farmatica
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import com.farmatica.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth

class MainActivity : AppCompatActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient

    //Definimos un objeto para acceder a la autenticacion de firebase
    private lateinit var auth: FirebaseAuth
    //Definimos un objeto para acceder a los elemento de la pantalla xml
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth


        //Se define el metodo para hacer el login
        binding.btIniciarSesion.setOnClickListener { haceLogin() }
        //Se define el metodo para hacer el registro de usuario
        binding.btRegistrarse.setOnClickListener { haceRegistro() }

        //Google sign in Option
        val gso
        = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id_r))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)
        binding.btGoogle.setOnClickListener { googleSignIn() }



    }

    private fun googleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent,5000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 5000){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val cuenta = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(cuenta.idToken!!)
            } catch (e: ApiException){

            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this){task->
                if (task.isSuccessful){
                    val user = auth.currentUser
                    actualiza(user)
                }else{
                    actualiza(null)
                }
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
            val intent = Intent(this, Menu_Principal::class.java)
            startActivity(intent)
        }
    }

    //Esto hara que una vez autenticado.... no pida mas autenticarse a menos que se cierre la sesion
    //public override fun onStart() {
    //    super.onStart()
    //    val usuario = auth.currentUser
    //    actualiza(usuario)
    //}


}