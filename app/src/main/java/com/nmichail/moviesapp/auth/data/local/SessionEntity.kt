package com.nmichail.moviesapp.auth.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SessionEntity(
    @PrimaryKey val sessionId: String,
    val isGuestSession: Boolean
)
