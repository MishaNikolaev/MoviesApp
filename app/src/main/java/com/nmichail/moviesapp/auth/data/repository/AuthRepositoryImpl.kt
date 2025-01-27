package com.nmichail.moviesapp.auth.data.repository

import com.nmichail.moviesapp.auth.data.dto.LoginRequestTokenBody
import com.nmichail.moviesapp.auth.data.remote.AuthApi
import com.nmichail.moviesapp.auth.domain.models.GuestSession
import com.nmichail.moviesapp.auth.domain.models.Session
import com.nmichail.moviesapp.auth.domain.models.Token
import com.nmichail.moviesapp.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {

    private val apiKey = "YOUR_API_KEY"

    override suspend fun createGuestSession(): GuestSession {
        val response = authApi.createGuestSession(apiKey)
        return GuestSession(
            success = response.success,
            guestSessionId = response.guest_session_id,
            expiresAt = response.expires_at
        )
    }

    override suspend fun createRequestToken(): Token {
        val response = authApi.createRequestToken(apiKey)
        return Token(
            success = response.success,
            requestToken = response.request_token,
            expiresAt = response.expires_at
        )
    }

    override suspend fun validateTokenWithLogin(
        username: String,
        password: String,
        token: String
    ): Token {
        val response = authApi.validateWithLogin(
            apiKey,
            LoginRequestTokenBody(username, password, token)
        )
        return Token(
            success = response.success,
            requestToken = response.request_token,
            expiresAt = response.expires_at
        )
    }

    override suspend fun createSession(requestToken: String): Session {
        val response = authApi.createSession(apiKey, mapOf("request_token" to requestToken))
        return Session(
            success = response.success,
            sessionId = response.session_id
        )
    }
}