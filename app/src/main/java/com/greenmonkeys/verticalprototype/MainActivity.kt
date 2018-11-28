package com.greenmonkeys.verticalprototype

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.jtorres.handmadeverticalprototype.R
import com.greenmonkeys.verticalprototype.adapters.ArtisanListRecyclerAdapter
import com.greenmonkeys.verticalprototype.model.ArtisanContainer
import com.greenmonkeys.verticalprototype.model.persistence.AppDatabase
import com.greenmonkeys.verticalprototype.model.persistence.DatabaseContainer
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_artisan)

        val artisanListRecycler =
            findViewById<RecyclerView>(R.id.artisanListRecycler)
        artisanListRecycler.layoutManager = LinearLayoutManager(this)
        doAsync {
            val artisanList =
                DatabaseContainer.getDatabase(applicationContext).artisanDao().getAll().map { it.toModel() }

            uiThread {
                artisanListRecycler.adapter = ArtisanListRecyclerAdapter(artisanList)
            }
        }

        val addArtisanButton = findViewById<FloatingActionButton>(R.id.addArtisan)
        addArtisanButton.setOnClickListener {
            startActivity(
                Intent(this,CreateArtisanActivity::class.java)
            )
        }
    }

    override fun onPause() {
        doAsync {
            DatabaseContainer.getDatabase(applicationContext).artisanDao()
                .insertAll(ArtisanContainer.artisans.map { it.toDataClass() }.toList())
        }
        super.onPause()
    }
}
