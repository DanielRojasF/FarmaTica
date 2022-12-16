package com.farmatica.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.farmatica.model.Propietario
import com.farmatica.model.Tarjeta
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase

class PropietarioDao {

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

    //Se recibe un objeto propietario, se valida si el id tiene algo... es una actualizacion, sino se creo
    fun savePropietario(propietario: Propietario){
        val documento : DocumentReference
        if (propietario.idPropetario.isEmpty()) {
            documento = firestore
                .collection(coleccion1)
                .document(usuario)
                .collection(coleccion2)
                .document()
            propietario.idPropetario = documento.id
        }else{//Si el id tenia algo... entonces ubico ese id como el  documento...
            documento = firestore
                .collection(coleccion1)
                .document(usuario)
                .collection(coleccion2)
                .document(propietario.idPropetario)
        }

        //ahora si se va a registrar la info (nueva o actualiza)
        //"registra" la actualizacion
        documento.set(propietario)
            .addOnSuccessListener {
                Log.d("savePropietario","propietario agregado/actualizado")
            }
            .addOnCanceledListener {
                Log.e("savePropietario","propietario NO agregado/actualizado")
            }
    }

    fun getPropietario() : MutableLiveData<List<Propietario>> {
        val listaPropietario = MutableLiveData<List<Propietario>>()

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
                    val lista = ArrayList<Propietario>()
                    //Se recorre la instantanea para transformar los documentos en un objeto propietario
                    instantanea.documents.forEach {
                        val propietario = it.toObject(Propietario::class.java)
                        if (propietario != null) {//Si se transformo en un objeto propietario... el documento
                            lista.add(propietario)//se agrega al propietario a la lista
                        }
                    }
                    listaPropietario.value = lista
                }
            }
        return listaPropietario
    }
}