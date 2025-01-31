package com.nmichail.moviesapp.profile.domain.usecases

import com.nmichail.moviesapp.profile.data.repository.ProfileRepository
import com.nmichail.moviesapp.profile.domain.models.Profile
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(accountId: Long, sessionId: String): Profile {
        return repository.getProfile(accountId, sessionId)
    }
}