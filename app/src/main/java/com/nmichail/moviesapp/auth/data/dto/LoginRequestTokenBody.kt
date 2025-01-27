package com.nmichail.moviesapp.auth.data.dto

data class LoginRequestTokenBody(
    val username: String,
    val password: String,
    val request_token: String
)