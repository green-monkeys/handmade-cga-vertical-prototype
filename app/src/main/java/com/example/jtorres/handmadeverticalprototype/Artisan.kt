package com.example.jtorres.handmadeverticalprototype

import android.support.v7.widget.RecyclerView

class Artisan(val firstName: String, val lastName: String, val email: String) {
    override fun toString(): String {
        return "$firstName $lastName"
    }
}