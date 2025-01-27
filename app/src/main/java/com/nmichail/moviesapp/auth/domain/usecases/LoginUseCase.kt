package com.nmichail.moviesapp.auth.domain.usecases
import com.nmichail.moviesapp.auth.domain.models.Session

import com.nmichail.moviesapp.auth.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Session {
        val token = authRepository.createRequestToken()
        val validatedToken = authRepository.validateTokenWithLogin(
            username, password, token.requestToken
        )
        return authRepository.createSession(validatedToken.requestToken)
    }
}