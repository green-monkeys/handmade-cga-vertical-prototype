package com.example.jtorres.handmadeverticalprototype

object ArtisanContainer {
    val artisans = mutableListOf(
        Artisan("Steve", "Dude", "steve@email.com"),
        Artisan("Dave", "Other", "dave@email.com")
    )

    fun addArtisan(artisan: Artisan) {
        artisans.add(artisan)
    }
}