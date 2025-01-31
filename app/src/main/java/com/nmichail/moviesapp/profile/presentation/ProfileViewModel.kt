package com.nmichail.moviesapp.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nmichail.moviesapp.profile.domain.usecases.GetProfileUseCase
import com.nmichail.moviesapp.profile.domain.usecases.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state

    fun handleEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.LoadProfile -> loadProfile()
            is ProfileEvent.SignOut -> signOut()
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val profile = getProfileUseCase(accountId = 21776632, sessionId = "SESSION_ID_HERE")

                _state.value = _state.value.copy(
                    isLoading = false,
                    avatarUrl = profile.avatarUrl,
                    username = profile.username,
                    email = profile.email,
                    error = null
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    private fun signOut() {
        viewModelScope.launch {
            signOutUseCase().onSuccess {
            }.onFailure {
                _state.value = _state.value.copy(
                    error = "Failed to sign out"
                )
            }
        }
    }
}