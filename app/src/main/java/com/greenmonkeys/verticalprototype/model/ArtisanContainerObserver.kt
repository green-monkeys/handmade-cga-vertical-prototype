package com.greenmonkeys.verticalprototype.model

interface ArtisanContainerObserver {
    fun onArtisansUpdated(newArtisans: List<Artisan>)
}