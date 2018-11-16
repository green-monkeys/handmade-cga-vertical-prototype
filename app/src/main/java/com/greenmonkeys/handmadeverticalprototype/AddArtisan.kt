package com.greenmonkeys.handmadeverticalprototype

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.greenmonkeys.handmadeverticalprototype.R

class AddArtisan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_artisan)

        setSupportActionBar(findViewById(R.id.add_artisan_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    } 
}
