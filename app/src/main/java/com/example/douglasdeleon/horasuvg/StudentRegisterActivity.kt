package com.example.douglasdeleon.horasuvg

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_admin_register.*
import kotlinx.android.synthetic.main.activity_student_register.*

class StudentRegisterActivity : AppCompatActivity() {
    val PICK_PHOTO_CODE = 1046
    override fun onCreate(savedInstanceState: Bundle?) {
        var RESULT_LOAD_IMAGE:Int =1;

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_register)
        studentImageUpload.setOnClickListener {

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

            studentImageUpload.setImageBitmap(selectedImage);
            Toast.makeText(this@StudentRegisterActivity, "Imagen cargada con éxito.. ", Toast.LENGTH_SHORT).show();
        }
    }
}
