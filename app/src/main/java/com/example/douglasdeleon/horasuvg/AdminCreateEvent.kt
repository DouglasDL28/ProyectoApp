package com.example.douglasdeleon.horasuvg

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.douglasdeleon.horasuvg.Model.MyApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.admin_create_event.*

class AdminCreateEvent: Fragment() {

    private var mFirebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var thisContext: Context? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        thisContext = container!!.context
        return inflater.inflate(com.example.douglasdeleon.horasuvg.R.layout.admin_create_event, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //you can set the title for your toolbar here for different fragments different titles
        activity!!.title = "Crear Evento"

        buttonCreate.setOnClickListener {
            createEvent()
        }
    }

    private fun createEvent(){
        val nameStr = name_editText.text.toString()
        val descriptionStr = description_editText.text.toString()
        val placeStr = place_editText.text.toString()
        val dateStr = date_editText.text.toString()
        var cancel = false
        var message = ""

        if(nameStr==""){
            message="El nombre no puede estar vacío."
            cancel = true
        }else if(descriptionStr==""){
            message="La descripción no puede estar vacía."
            cancel = true
        }else if(placeStr==""){
            message="El lugar no puede estar vacío."
            cancel = true
        }else if(dateStr==""){
            message="La fecha no puede estar vacía."
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            // Initialize a new instance of
            val builder = AlertDialog.Builder(thisContext!!)

            // Enviar alerta
            builder.setTitle("Error")

            // Mostrar mensaje de alerta si los datos no son validos
            builder.setMessage("$message")
            builder.setPositiveButton("Ok"){dialog, which ->

            }
            builder.show()

        } else {
//            var newEvent: Event = Event(nameStr,descriptionStr,placeStr,dateStr)

            //Probar con HashMap.
            val evento = HashMap<String, Any>()
            evento["name"] = nameStr
            evento["description"] = descriptionStr
            evento["location"] = placeStr
            evento["date"] = dateStr

            //Agregar el evento a collección "events" con ID [nameStr].
            db.collection("events")
                .document(nameStr)
                .set(evento)
                .addOnSuccessListener { Log.d(TAG, "Se agregó el evento con éxito." ) }
                .addOnFailureListener {e -> Log.d(TAG, "Ocurrió un error.", e) }

            //Agregar evento a subcolección de eventos del Admin que lo creó.
            db.collection("administrators").document(MyApplication.userInsideId)
                .collection("adminEvents")
                .document(nameStr)
                .set(evento)

            var fragmentManager: FragmentManager = fragmentManager!!
            var fragment: Fragment = Start()
            fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit()
            Toast.makeText(thisContext, "Se ha creado el evento correctamente", Toast.LENGTH_LONG).show()
        }

    }
}