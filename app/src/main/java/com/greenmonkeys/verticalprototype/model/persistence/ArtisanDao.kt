package com.greenmonkeys.verticalprototype.model.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ArtisanDao {
    @Query("SELECT * FROM PArtisan")
    fun getAll(): List<PArtisan>

    @Query("SELECT * FROM PArtisan WHERE email LIKE :email")
    fun findByEmail(email: String): PArtisan

    @Insert
    fun insert(pArtisan: PArtisan)

    @Insert
    fun insertAll(pArtisans: List<PArtisan>)

    @Delete
    fun delete(pArtisan: PArtisan)
}