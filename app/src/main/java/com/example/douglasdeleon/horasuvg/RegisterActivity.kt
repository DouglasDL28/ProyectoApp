package com.example.douglasdeleon.horasuvg

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class RegisterActivity : AppCompatActivity() {

    fun adminRegisterButton (view: View) {
        val intent = Intent(this@RegisterActivity, AdminRegisterActivity::class.java)
        startActivity(intent)
    }

    fun studentRegisterButton (view: View) {
        val intent = Intent(this@RegisterActivity, StudentRegisterActivity::class.java)
        startActivity(intent)
    }

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
}
