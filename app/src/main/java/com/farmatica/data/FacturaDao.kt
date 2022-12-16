package com.farmatica.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.farmatica.model.Factura
import com.farmatica.model.Propietario
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase

class FacturaDao {
    //valores para la estructura de firestore cloud
    private val coleccion1 = "Informacion_Propietario"
    private val usuario = Firebase.auth.currentUser?.email.toString()
    private val coleccion2 = "miInformacion"

    //objeto para la "conexion" de la base de datos en la nude
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init{
        //Como que inicializa la conexion con firestore para poder trabajar
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    //CRUD

    //Se recibe un objeto factura, se valida si el id tiene algo... es una actualizacion, sino se creo
    fun saveFactura(factura: Factura){
        val documento : DocumentReference
        if (factura.idFactura.isEmpty()) {
            documento = firestore
                .collection(coleccion1)
                .document(usuario)
                .collection(coleccion2)
                .document()
            factura.idFactura = documento.id
        }else{//Si el id tenia algo... entonces ubico ese id como el  documento...
            documento = firestore
                .collection(coleccion1)
                .document(usuario)
                .collection(coleccion2)
                .document(factura.idFactura)
        }

        //ahora si se va a registrar la info (nueva o actualiza)
        //"registra" la actualizacion
        documento.set(factura)
            .addOnSuccessListener {
                Log.d("saveFactura","factura agregado/actualizado")
            }
            .addOnCanceledListener {
                Log.e("saveFactura","factura NO agregado/actualizado")
            }
    }

    fun getFacturas() : MutableLiveData<List<Factura>> {
        val listaFactura = MutableLiveData<List<Factura>>()

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
                    val lista = ArrayList<Factura>()
                    //Se recorre la instantanea para transformar los documentos en un objeto factura
                    instantanea.documents.forEach {
                        val factura = it.toObject(Factura::class.java)
                        if (factura != null) {//Si se transformo en un objeto factura... el documento
                            lista.add(factura)//se agrega la factura a la lista
                        }
                    }
                    listaFactura.value = lista
                }
            }
        return listaFactura
    }
}