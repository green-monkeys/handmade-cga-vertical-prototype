package com.greenmonkeys.handmadeverticalprototype

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.support.v7.widget.Toolbar;
import android.view.Menu
import android.view.MenuInflater

import com.greenmonkeys.handmadeverticalprototype.R

class Artisans : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_add_artisan -> {
            // User chose the "Add Artisan"... go to Add Artisan Activity
            val intent = Intent(this, AddArtisan::class.java)
            startActivity(intent)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.artisan_menu, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artisans)

        setSupportActionBar(findViewById(R.id.artisan_toolbar))
    }
}
