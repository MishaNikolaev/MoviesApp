package com.nmichail.moviesapp.auth.data.dto

data class RequestTokenResponse(
    val success: Boolean,
    val expires_at: String,
    val request_token: String
)