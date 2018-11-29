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

    @Test
    fun artisanConstructorTest2() {
        val artisan = Artisan("Joe", "Schmoe", "jschmoe@mail.com")
        assertEquals("Joe Schmoe", artisan.toString())
    }

    @Test
    fun artisanDataClassTest() {
        val artisan = Artisan("First", "Last", "email@mail.com")
        assertEquals("First", artisan.toDataClass().firstName)
        assertEquals("Last", artisan.toDataClass().lastName)
        assertEquals("email@mail.com", artisan.toDataClass().email)
    }

    @Test
    fun atisanDataClassTest2() {
        val artisan = Artisan("Nancy", "Turntable", "email@mail.com")
        assertEquals(artisan.toDataClass().toModel().toString(), "Nancy Turntable")
    }

    var artisan1 = Artisan("Edward","Lam","eddieSpam@email.com")
    var artisan2 = Artisan("Blue","Steel","zooMan@email.com")
    var artisan3 = Artisan("Grey","Fox","jaeger@email.com")


    @Test
    fun testToString() {
        assertTrue(artisan1.toString().equals("Edward Lam"))
        assertTrue(artisan2.toString().equals("Blue Steel"))
        assertTrue(artisan3.toString().equals("Grey Fox"))
    }

}