package com.nmichail.moviesapp.auth.domain.usecases

import com.nmichail.moviesapp.auth.data.dto.GuestSessionResponse
import com.nmichail.moviesapp.auth.domain.models.GuestSession
import com.nmichail.moviesapp.auth.domain.repository.AuthRepository

import javax.inject.Inject

class CreateGuestSessionUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): GuestSession {
        return authRepository.createGuestSession()
    }
}