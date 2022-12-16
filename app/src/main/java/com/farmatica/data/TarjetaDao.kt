package com.farmatica.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.farmatica.model.Tarjeta
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase

class TarjetaDao {
    //valores para la estructura de firestore cloud
    private val coleccion1 = "Tarjetas"
    private val usuario = Firebase.auth.currentUser?.email.toString()
    private val coleccion2 = "misTarjetas"

    //objeto para la "conexion" de la base de datos en la nude
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init{
        //Como que inicializa la conexion con firestore para poder trabajar
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    //CRUD

    //Se recibe un objeto tarjeta, se valida si el id tiene algo... es una actualizacion, sino se creo
    fun saveTarjeta(tarjeta: Tarjeta){
        val documento : DocumentReference
        if (tarjeta.idTarjeta.isEmpty()) {
            documento = firestore
                .collection(coleccion1)
                .document(usuario)
                .collection(coleccion2)
                .document()
            tarjeta.idTarjeta = documento.id
        }else{//Si el id tenia algo... entonces ubico ese id como el  documento...
            documento = firestore
                .collection(coleccion1)
                .document(usuario)
                .collection(coleccion2)
                .document(tarjeta.idTarjeta)
        }

        //ahora si se va a registrar la info (nueva o actualiza)
        //"registra" la actualizacion
        documento.set(tarjeta)
            .addOnSuccessListener {
                Log.d("saveTarjeta","tarjeta agregada/actualizada")
            }
            .addOnCanceledListener {
                Log.e("saveTarjeta","tarjeta NO agregada/actualizada")
            }
    }

    fun deleteTarjeta(tarjeta: Tarjeta){
        //se valida si el id tiene algo...
        if(tarjeta.idTarjeta.isNotEmpty()){
            firestore
                .collection(coleccion1)
                .document(usuario)
                .collection(coleccion2)
                .document(tarjeta.idTarjeta)
                .delete()
                .addOnSuccessListener {
                    Log.d("deleteTarjeta","tarjeta eliminada")
                }
                .addOnCanceledListener {
                    Log.e("deleteTarjeta","tarjeta NO eliminada")
                }
        }
    }

    fun getTarjetas() : MutableLiveData<List<Tarjeta>> {
        val listaTarjetas = MutableLiveData<List<Tarjeta>>()

        firestore
            .collection(coleccion1)
            .document(usuario)
            .collection(coleccion2)
            .addSnapshotListener { instantanea, error ->
                if (error != null) {//Se materializo algun error en la generacion de la vista instantanea
                    return@addSnapshotListener
                }
                //Si estamos en esta linea.. entonces si se tomo la instantanea
                if (instantanea != null) {//hay datos en la instantanea
                    val lista = ArrayList<Tarjeta>()
                    //Se recorre la instantanea para transformar los documentos en un objeto tarjeta
                    instantanea.documents.forEach {
                        val tarjeta = it.toObject(Tarjeta::class.java)
                        if (tarjeta != null) {//Si se transformo en un objeto tarjeta... el documento
                            lista.add(tarjeta)//se agrega la tarjeta a la lista
                        }
                    }
                    listaTarjetas.value = lista
                }
            }

        return listaTarjetas
    }
}