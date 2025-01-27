package com.nmichail.moviesapp.auth.domain.repository

import com.nmichail.moviesapp.auth.data.local.SessionEntity

interface AuthLocalRepository {

    suspend fun saveSession(session: SessionEntity)

    suspend fun getSession(): SessionEntity?

    suspend fun clearSession()
}