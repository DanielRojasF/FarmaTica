package com.farmatica

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.ktx.Firebase

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var productoList: ArrayList<Pruducto>
    private var db = Firabase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_producto)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
       productoList = arrayListOf()
        db = FirebaseFirestore.getInstance()
        db.collection("producto").get().addOnSuccessListener{
            if (!it.isEmpty){
                for (data in it.documents){
                    val producto: Pruducto? = data.toObject(Pruducto::class.java)
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