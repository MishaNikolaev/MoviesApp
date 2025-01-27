package com.nmichail.moviesapp.auth.domain.repository

import com.nmichail.moviesapp.auth.domain.models.GuestSession
import com.nmichail.moviesapp.auth.domain.models.Session
import com.nmichail.moviesapp.auth.domain.models.Token

interface AuthRepository {

    suspend fun createGuestSession(): GuestSession

    suspend fun createRequestToken(): Token

    suspend fun validateTokenWithLogin(username: String, password: String, token: String): Token

    suspend fun createSession(requestToken: String): Session

}