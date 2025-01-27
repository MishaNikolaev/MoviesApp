package com.nmichail.moviesapp.auth.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSession(session: SessionEntity)

    @Query("SELECT * FROM SessionEntity LIMIT 1")
    suspend fun getSession(): SessionEntity?

    @Query("DELETE FROM SessionEntity")
    suspend fun clearSessions()
}