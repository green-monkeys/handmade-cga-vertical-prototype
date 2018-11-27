package com.greenmonkeys.verticalprototype.model.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.greenmonkeys.verticalprototype.model.Artisan


@Entity(tableName = "Artisan")
data class Artisan (
    @PrimaryKey var email: String,
    @ColumnInfo(name="first_name") var firstName: String,
    @ColumnInfo(name="last_name") var lastName: String
) {
    fun toModel(): Artisan {
        return Artisan(firstName, lastName, email)
    }
}