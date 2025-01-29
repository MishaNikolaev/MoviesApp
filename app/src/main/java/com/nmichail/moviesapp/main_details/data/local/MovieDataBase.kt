package com.nmichail.moviesapp.main_details.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieDetailsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDetailsDatabase : RoomDatabase() {
    abstract fun movieDetailsDao(): MovieDetailsDao
}