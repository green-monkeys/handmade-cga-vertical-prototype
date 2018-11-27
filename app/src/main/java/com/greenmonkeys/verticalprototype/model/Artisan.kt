package com.greenmonkeys.verticalprototype.model

import android.support.v7.widget.RecyclerView
import com.greenmonkeys.verticalprototype.model.persistence.Artisan

class Artisan(private val firstName: String, private val lastName: String, private val email: String) {
    override fun toString(): String {
        return "$firstName $lastName"
    }

    fun toDataClass(): Artisan {
        return Artisan(email, firstName, lastName)
    }
}