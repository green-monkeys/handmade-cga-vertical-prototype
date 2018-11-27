package com.greenmonkeys.handmadeverticalprototype.handmadeverticalprototype

import com.greenmonkeys.verticalprototype.model.Artisan
import org.junit.Test

import org.junit.Assert.*

class ArtisanTest {
    @Test
    fun artisanConstructorTest() {
        val artisan = Artisan("John", "Doe", "email@mail.com")
        assertEquals( "John Doe", artisan.toString())
    }
}