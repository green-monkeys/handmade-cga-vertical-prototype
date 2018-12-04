package com.greenmonkeys.verticalprototype.model

import android.content.Context
import com.greenmonkeys.verticalprototype.model.persistence.DatabaseContainer
import org.jetbrains.anko.doAsync
import kotlin.properties.Delegates

object ArtisanContainer {
    var observers = ArrayList<ArtisanContainerObserver>()
    var artisans: List<Artisan> by Delegates.observable(ArrayList()) { _, _, new ->
        observers.forEach { it.onArtisansUpdated(new) }
    }

    fun observe(observer: ArtisanContainerObserver) {
        observers.add(observer)
    }

    fun addArtisan(artisan: Artisan) {
        artisans += artisan
    }

    fun addArtisans(artisanList: List<Artisan>) {
        artisans += artisanList.filter { it !in artisans }
    }

    fun getNextID(): Long {
        return artisans.size.toLong()
    }

    fun saveArtisansToLocalDB(applicationContext: Context) {
        doAsync {
            val currentlyStoredArtisans = DatabaseContainer.getDatabase(applicationContext).artisanDao().getAll().map { it.toModel() }
            val notStoredPArtisans = artisans.filter { it !in currentlyStoredArtisans }.map { it.toDataClass() }
            DatabaseContainer.getDatabase(applicationContext).artisanDao().insertAll(notStoredPArtisans)
        }
    }

    fun populateArtisansFromLocalDB(applicationContext: Context) {
        doAsync {
            val currentlyStoredArtisans = DatabaseContainer.getDatabase(applicationContext).artisanDao().getAll().map { it.toModel() }
            val nonPopulatedArtisans = currentlyStoredArtisans.filter { it !in artisans }
            addArtisans(nonPopulatedArtisans)
        }
    }
}