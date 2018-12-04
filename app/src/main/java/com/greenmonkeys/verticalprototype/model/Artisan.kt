package com.greenmonkeys.verticalprototype.model

import android.graphics.BitmapFactory
import com.greenmonkeys.verticalprototype.model.persistence.PArtisan

class Artisan(val uid: Long, val firstName: String, val lastName: String, val email: String, val imageFileLocation: String) {
    val bitmap = BitmapFactory.decodeFile(imageFileLocation)

    override fun toString(): String {
        return "$firstName $lastName"
    }

    fun toDataClass(): PArtisan {
        return PArtisan(uid, email, firstName, lastName, imageFileLocation)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Artisan

        if (uid != other.uid) return false

        return true
    }

    override fun hashCode(): Int {
        return uid.hashCode()
    }


}