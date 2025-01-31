package com.nmichail.moviesapp.profile.presentation

data class ProfileState(
    val isLoading: Boolean = false,
    val avatarUrl: String? = null,
    val username: String? = null,
    val email: String? = null,
    val error: String? = null
)