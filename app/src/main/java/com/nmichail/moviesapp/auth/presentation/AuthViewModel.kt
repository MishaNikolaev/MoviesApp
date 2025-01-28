package com.nmichail.moviesapp.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nmichail.moviesapp.auth.data.local.SessionEntity
import com.nmichail.moviesapp.auth.domain.repository.AuthLocalRepository
import com.nmichail.moviesapp.auth.domain.usecases.CreateGuestSessionUseCase
import com.nmichail.moviesapp.auth.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val createGuestSessionUseCase: CreateGuestSessionUseCase,
    private val loginUseCase: LoginUseCase,
    private val authLocalRepository: AuthLocalRepository
) : ViewModel() {

    private val _state = MutableStateFlow<AuthState>(AuthState.Idle)
    val state: StateFlow<AuthState> = _state

    init {
        restoreSession()
    }

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.CreateGuestSession -> createGuestSession()
            is AuthEvent.Login -> login(event.username, event.password)
        }
    }

    private fun createGuestSession() {
        viewModelScope.launch {
            _state.value = AuthState.Loading
            try {
                val response = createGuestSessionUseCase()
                saveSession(response.guestSessionId, isGuest = true)
                _state.value = AuthState.GuestSessionCreated(response.guestSessionId)
            } catch (e: Exception) {
                _state.value = AuthState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun login(username: String, password: String) {
        viewModelScope.launch {
            _state.value = AuthState.Loading
            try {
                val response = loginUseCase(username, password)
                saveSession(response.sessionId, isGuest = false)
                _state.value = AuthState.LoginSuccess(response.sessionId)
            } catch (e: Exception) {
                _state.value = AuthState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun saveSession(sessionId: String, isGuest: Boolean) {
        viewModelScope.launch {
            authLocalRepository.saveSession(SessionEntity(sessionId, isGuest))
        }
    }

    private fun restoreSession() {
        viewModelScope.launch {
            val session = authLocalRepository.getSession()
            if (session != null) {
                _state.value = if (session.isGuestSession) {
                    AuthState.GuestSessionCreated(session.sessionId)
                } else {
                    AuthState.LoginSuccess(session.sessionId)
                }
            }
        }
    }

    fun clearSession() {
        viewModelScope.launch {
            authLocalRepository.clearSession()
            _state.value = AuthState.Idle
        }
    }
}