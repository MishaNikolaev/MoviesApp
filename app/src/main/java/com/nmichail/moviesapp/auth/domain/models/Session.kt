package com.nmichail.moviesapp.auth.domain.models

data class Session(
    val success: Boolean,
    val sessionId: String
)