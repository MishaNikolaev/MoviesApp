package com.nmichail.moviesapp.auth.domain.models

data class GuestSession(
    val success: Boolean,
    val guestSessionId: String,
    val expiresAt: String
)