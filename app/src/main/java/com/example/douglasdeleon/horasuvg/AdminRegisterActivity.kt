package com.example.douglasdeleon.horasuvg

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_admin_register.*
import kotlinx.android.synthetic.main.activity_login.*
import android.provider.MediaStore

class AdminRegisterActivity : AppCompatActivity() {
    val PICK_PHOTO_CODE = 1046
    private var mFirebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        var RESULT_LOAD_IMAGE:Int =1;
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_register)

        adminImageUpload.setOnClickListener {

            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )

            // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
            // So as long as the result is not null, it's safe to use the intent.
            if (intent.resolveActivity(packageManager) != null) {
                // Bring up gallery to select a photo
                startActivityForResult(intent, PICK_PHOTO_CODE)
            }
        }

    }
	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //https://github.com/codepath/android_guides/wiki/Accessing-the-Camera-and-Stored-Media
        if (data != null) {
            var photoUri: Uri = data.getData();
            // Do something with the photo based on Uri
            var selectedImage: Bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, photoUri);
            // Load the selected image into a preview

            adminImageUpload.setImageBitmap(selectedImage);
            Toast.makeText(this@AdminRegisterActivity, "Imagen cargada con éxito.. ", Toast.LENGTH_SHORT).show();
            //Inicializa FireBase
            mFirebaseAuth = FirebaseAuth.getInstance();

            okbuttona.setOnClickListener {
                register()
            }
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
