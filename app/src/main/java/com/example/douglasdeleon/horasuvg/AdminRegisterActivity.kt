package com.example.douglasdeleon.horasuvg

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_admin_register.*
import kotlinx.android.synthetic.main.activity_login.*

class AdminRegisterActivity : AppCompatActivity() {

    private var mFirebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_register)

        //Inicializa FireBase
        mFirebaseAuth = FirebaseAuth.getInstance();

        ok_button.setOnClickListener {
            register()
        }
    }

    private fun register()  {
        val emailStr = admin_mail_textview.text.toString()
        val passwordStr = admin_password_textview.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(passwordStr) && !isPasswordValid(passwordStr)) {
            password.error = getString(R.string.error_invalid_password)
            focusView = password
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(emailStr)) {
            email.error = getString(R.string.error_field_required)
            focusView = email
            cancel = true
        } else if (!isEmailValid(emailStr)) {
            email.error = getString(R.string.error_invalid_email)
            focusView = email
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {

            mFirebaseAuth!!.createUserWithEmailAndPassword(emailStr, passwordStr).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Se ha creado el usuario correctamente", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "No se ha podido crear el usuario, revisa tus datos", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        //TODO: Replace this with your own logic
        return email.contains("@uvg.edu.gt")
    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length > 8
    }


}
