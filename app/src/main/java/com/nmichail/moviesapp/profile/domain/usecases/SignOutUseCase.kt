package com.nmichail.moviesapp.profile.domain.usecases

import com.nmichail.moviesapp.profile.data.repository.ProfileRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.signOut()
    }
}