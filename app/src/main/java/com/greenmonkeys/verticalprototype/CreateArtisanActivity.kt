package com.greenmonkeys.verticalprototype

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.MenuItem
import android.widget.TextView
import com.example.jtorres.handmadeverticalprototype.R
import com.greenmonkeys.verticalprototype.model.Artisan
import com.greenmonkeys.verticalprototype.model.ArtisanContainer

class CreateArtisanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_artisan)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val firstNameField = findViewById<TextView>(R.id.first_name_field)
        val lastNameField = findViewById<TextView>(R.id.last_name_field)
        val emailField = findViewById<TextView>(R.id.email_field)

        val saveArtisanButton = findViewById<FloatingActionButton>(R.id.save_artisan_button)
        saveArtisanButton.setOnClickListener {
            if (firstNameField.text != null && lastNameField.text != null && emailField.text != null) {
                ArtisanContainer.addArtisan(
                    Artisan(
                        firstNameField.text.toString(),
                        lastNameField.text.toString(),
                        emailField.text.toString()
                    )
                )

                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    // TODO: Figure out what ID to use to get the animation working
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.homeAsUp || item?.itemId == R.id.home) {
            finish()
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            return true
        }
        return false
    }
}
