package com.greenmonkeys.handmadeverticalprototype.handmadeverticalprototype

import com.greenmonkeys.verticalprototype.model.Artisan
import com.greenmonkeys.verticalprototype.model.ArtisanContainer
import org.junit.Assert
import org.junit.Test

class ArtisanContainerTest {
    var tempContainer = ArtisanContainer
    var artisan1 = Artisan("Edward","Lam","eddieSpam@email.com")
    var artisan2 = Artisan("Blue","Steel","zooMan@email.com")
    var artisan3 = Artisan("Grey","Fox","jaeger@email.com")

    @Test
    fun addSingleArtisan() {
        tempContainer.addArtisan(artisan1)
        Assert.assertTrue(tempContainer.artisans.contains(artisan1))
    }

    @Test
    fun addArtisan() {
        tempContainer.addArtisan(artisan2)
        Assert.assertTrue(tempContainer.artisans.contains(artisan2))
        tempContainer.addArtisan(artisan3)
        Assert.assertTrue(tempContainer.artisans.contains(artisan3))
    }

    @Test
    fun testArtisanData() {
        val container = ArtisanContainer
        container.addArtisan(Artisan("Melanie", "Grindelwald", "mgrin@example.org"))
        Assert.assertFalse(container.artisans.isEmpty())
    }

    @Test
    fun testArtisanData2() {
        val container = ArtisanContainer
        val numArtisans = container.artisans.size
        container.addArtisan(Artisan("Mason", "Lundt", "mlund@email.com"))
        container.artisans.removeAt(0)
        Assert.assertEquals(container.artisans.size, numArtisans)
    }
}