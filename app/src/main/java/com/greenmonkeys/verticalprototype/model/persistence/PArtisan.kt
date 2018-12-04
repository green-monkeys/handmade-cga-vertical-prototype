package com.greenmonkeys.verticalprototype.model.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.greenmonkeys.verticalprototype.model.Artisan


@Entity(tableName = "PArtisan")
data class PArtisan (
    @PrimaryKey var uid: Long,
    @ColumnInfo(name="email") var email: String,
    @ColumnInfo(name="first_name") var firstName: String,
    @ColumnInfo(name="last_name") var lastName: String,
    @ColumnInfo(name="profile_picture") var profilePicture: String
) {
    fun toModel(): Artisan {
        return Artisan(uid, firstName, lastName, email, profilePicture)
    }
}