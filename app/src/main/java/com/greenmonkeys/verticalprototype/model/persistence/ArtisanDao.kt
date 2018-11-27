package com.greenmonkeys.verticalprototype.model.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ArtisanDao {
    @Query("SELECT * FROM Artisan")
    fun getAll(): List<Artisan>

    @Query("SELECT * FROM Artisan WHERE email LIKE :email")
    fun findByEmail(email: String): Artisan

    @Insert
    fun insert(artisan: Artisan)

    @Insert
    fun insertAll(artisans: List<Artisan>)

    @Delete
    fun delete(artisan: Artisan)
}