package com.example.douglasdeleon.horasuvg

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.douglasdeleon.horasuvg.Model.MyApplication
import kotlinx.android.synthetic.main.activity_logged_in.*
import kotlinx.android.synthetic.main.app_bar_logged_in.*
import kotlinx.android.synthetic.main.nav_header_logged_in.*

class LoggedIn : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)
        setSupportActionBar(toolbar)
        name.text=MyApplication.userInside.name
        email.text =MyApplication.userInside.email


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.logged_in, menu)
        return true
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_hours -> {
                // Handle the camera action
            }
            R.id.nav_activities -> {

            }

            R.id.nav_logout -> {
                val intent: Intent = Intent(this, LoginActivity::class.java);
                startActivity(intent);
                Toast.makeText(this, "Sesión cerrada correctamente.", Toast.LENGTH_LONG).show()
            }
            R.id.nav_settings -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
