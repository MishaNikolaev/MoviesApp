package com.nmichail.moviesapp.auth.data.repository

import com.nmichail.moviesapp.auth.data.local.SessionDao
import com.nmichail.moviesapp.auth.data.local.SessionEntity
import com.nmichail.moviesapp.auth.domain.repository.AuthLocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val sessionDao: SessionDao
) : AuthLocalRepository {

    override suspend fun saveSession(session: SessionEntity) {
        sessionDao.saveSession(session)
    }

    override suspend fun getSession(): SessionEntity? {
        return sessionDao.getSession()
    }

    override suspend fun clearSession() {
        sessionDao.clearSessions()
    }
}