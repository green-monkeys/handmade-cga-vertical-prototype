package com.greenmonkeys.handmadeverticalprototype.handmadeverticalprototype

import com.greenmonkeys.verticalprototype.model.Artisan
import com.greenmonkeys.verticalprototype.model.ArtisanContainer
import org.junit.Test

import org.junit.Assert.*

class ArtisanTest {
    @Test
    fun artisanConstructorTest() {
        val artisan = Artisan("John", "Doe", "email@mail.com")
        assertEquals( "John Doe", artisan.toString())
    }

    var tempContainer = ArtisanContainer
    var artisan1 = Artisan("Edward","Lam","eddieSpam@email.com")
    var artisan2 = Artisan("Blue","Steel","zooMan@email.com")
    var artisan3 = Artisan("Grey","Fox","jaeger@email.com")

    @Test
    fun addSingleArtisan() {
        tempContainer.addArtisan(artisan1)
        assertTrue(tempContainer.artisans.contains(artisan1))
    }

    @Test
    fun addArtisan() {
        tempContainer.addArtisan(artisan2)
        assertTrue(tempContainer.artisans.contains(artisan2))
        tempContainer.addArtisan(artisan3)
        assertTrue(tempContainer.artisans.contains(artisan3))
    }
    
}