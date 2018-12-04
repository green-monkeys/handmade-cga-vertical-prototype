package com.greenmonkeys.verticalprototype

import android.app.Activity
import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.greenmonkeys.verticalprototype.adapters.ArtisanListRecyclerAdapter
import com.greenmonkeys.verticalprototype.model.Artisan
import com.greenmonkeys.verticalprototype.model.ArtisanContainer
import com.greenmonkeys.verticalprototype.model.ArtisanContainerObserver
import com.greenmonkeys.verticalprototype.model.persistence.AppDatabase
import com.greenmonkeys.verticalprototype.model.persistence.DatabaseContainer
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.uiThread

const val CREATE_ARTISAN_INTENT_CODE = 1
const val TAKE_PICTURE_INTENT_CODE = 2

class MainActivity : AppCompatActivity(), ArtisanContainerObserver {
    private var artisanListRecycler: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_artisan)

        ArtisanContainer.observe(this)
        ArtisanContainer.populateArtisansFromLocalDB(applicationContext)
        artisanListRecycler = findViewById(R.id.artisanListRecycler)
        artisanListRecycler!!.adapter = ArtisanListRecyclerAdapter(ArtisanContainer.artisans, artisanListRecycler!!)
        artisanListRecycler?.layoutManager = LinearLayoutManager(applicationContext)
        /*
        doAsync {
            val artisanList = DatabaseContainer.getDatabase(applicationContext).artisanDao().getAll().map { it.toModel() }
            ArtisanContainer.addArtisans(artisanList)

            uiThread {
                artisanListRecycler!!.adapter = ArtisanListRecyclerAdapter(artisanList, artisanListRecycler!!)
                artisanListRecycler?.layoutManager = LinearLayoutManager(applicationContext)
            }
        }
        */

        val addArtisanButton = findViewById<FloatingActionButton>(R.id.addArtisan)
        addArtisanButton.setOnClickListener {
            Intent(this, CreateArtisanActivity::class.java).also { createArtisanIntent ->
                createArtisanIntent.resolveActivity(packageManager)?.also {
                    startActivityForResult(createArtisanIntent, 1)
                }
            }
        }
    }

    override fun onArtisansUpdated(newArtisans: List<Artisan>) {
        (artisanListRecycler?.adapter as ArtisanListRecyclerAdapter).setValues(newArtisans)
        runOnUiThread { artisanListRecycler?.adapter?.notifyDataSetChanged() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }

    override fun onPause() {
        ArtisanContainer.saveArtisansToLocalDB(applicationContext)
        super.onPause()
    }
}
