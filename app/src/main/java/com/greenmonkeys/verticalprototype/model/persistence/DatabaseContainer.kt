package com.greenmonkeys.verticalprototype.model.persistence

import android.arch.persistence.room.Room
import android.content.Context

object DatabaseContainer {
    private var db: AppDatabase? = null

    fun getDatabase(applicationContext: Context): AppDatabase {
        if (db == null) {
            db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "handmade"
            ).build()
        }
        return db!!
    }
}