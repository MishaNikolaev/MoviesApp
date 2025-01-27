package com.nmichail.moviesapp.auth.domain.models

data class Token(
    val success: Boolean,
    val requestToken: String,
    val expiresAt: String
)