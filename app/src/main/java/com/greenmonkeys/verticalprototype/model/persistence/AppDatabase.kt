package com.greenmonkeys.verticalprototype.model.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Artisan::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun artisanDao(): ArtisanDao
}