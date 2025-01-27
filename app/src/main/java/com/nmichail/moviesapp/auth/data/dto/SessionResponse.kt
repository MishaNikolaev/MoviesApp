package com.nmichail.moviesapp.auth.data.dto

data class SessionResponse(
    val success: Boolean,
    val session_id: String
)