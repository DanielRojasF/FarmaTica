package com.farmatica

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var productoList: ArrayList<Producto>
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_producto)


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        productoList = arrayListOf()

        db = FirebaseFirestore.getInstance()

        db.collection("Productos").get().addOnSuccessListener{
            if (!it.isEmpty){
                for (data in it.documents){
                    val producto: Producto? = data.toObject(Producto::class.java)
                    if (producto != null){

                        productoList.add(producto)
                    }
                }

                recyclerView.adapter = Myadapter(productoList)
            }

        }.addOnFailureListener {
            Toast.makeText(this,it.toString(), Toast.LENGTH_SHORT).show()

        }

    }
}