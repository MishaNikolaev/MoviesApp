package com.nmichail.moviesapp.auth.data.dto

data class GuestSessionResponse(
    val success: Boolean,
    val guest_session_id: String,
    val expires_at: String
)
