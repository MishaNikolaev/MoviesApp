package com.nmichail.moviesapp.profile.domain.repository

import com.nmichail.moviesapp.profile.data.remote.ProfileApi
import com.nmichail.moviesapp.profile.data.repository.ProfileRepository
import com.nmichail.moviesapp.profile.domain.models.Profile
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val api: ProfileApi
) : ProfileRepository {
    override suspend fun getProfile(accountId: Long, sessionId: String): Profile {
        val dto = api.getProfile(accountId, sessionId)

        val avatarUrl = when {
            dto.avatar.tmdb?.avatar_path != null -> "https://image.tmdb.org/t/p/w500${dto.avatar.tmdb.avatar_path}"
            dto.avatar.gravatar.hash.isNotEmpty() -> "https://www.gravatar.com/avatar/${dto.avatar.gravatar.hash}"
            else -> null
        }

        return Profile(
            avatarUrl = avatarUrl,
            username = dto.username,
            email = null
        )
    }

    override suspend fun signOut(): Result<Unit> {
        return Result.success(Unit)
    }
}