package com.nmichail.moviesapp.profile.data.repository

import com.nmichail.moviesapp.profile.domain.models.Profile


interface ProfileRepository {

    suspend fun getProfile(accountId: Long, sessionId: String): Profile

    suspend fun signOut(): Result<Unit>
}