package com.nmichail.moviesapp.auth.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SessionEntity::class], version = 1)
abstract class AuthDatabase : RoomDatabase() {
    abstract fun sessionDao(): SessionDao
}