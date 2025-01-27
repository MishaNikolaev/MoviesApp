package com.nmichail.moviesapp.auth.presentation

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class GuestSessionCreated(val sessionId: String) : AuthState()
    data class LoginSuccess(val sessionId: String) : AuthState()
    data class Error(val message: String) : AuthState()
}