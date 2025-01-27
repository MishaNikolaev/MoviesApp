package com.nmichail.moviesapp.auth.presentation

sealed class AuthEvent {
    object CreateGuestSession : AuthEvent()
    data class Login(val username: String, val password: String) : AuthEvent()
}