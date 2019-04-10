package com.example.douglasdeleon.horasuvg

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.example.douglasdeleon.horasuvg.Model.MyApplication
import com.example.douglasdeleon.horasuvg.Model.UserInside
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_admin_events.*

class AdminEventsActivity : AppCompatActivity() {

    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_events)

        recyclerAdminEvents.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL,false)

        db.collection("users").document(MyApplication.userInsideId).get()
            .addOnSuccessListener { documentSnapshot ->
                var user: UserInside = documentSnapshot.toObject(UserInside::class.java)!!
                MyApplication.userInside = user

                val intent: Intent = Intent(this, LoggedIn::class.java)
                startActivity(intent)
            }
            .addOnFailureListener { exception ->

            }

    }
}