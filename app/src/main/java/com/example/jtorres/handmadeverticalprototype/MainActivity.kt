package com.example.jtorres.handmadeverticalprototype

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_artisan)

        val artisanListRecycler = findViewById<RecyclerView>(R.id.artisanListRecycler)
        artisanListRecycler.layoutManager = LinearLayoutManager(this)
        artisanListRecycler.adapter = ArtisanListRecyclerAdapter(ArtisanContainer.artisans)

        val addArtisanButton = findViewById<FloatingActionButton>(R.id.addArtisan)
        addArtisanButton.setOnClickListener { startActivity(Intent(this, CreateArtisanActivity::class.java)) }
    }
}
