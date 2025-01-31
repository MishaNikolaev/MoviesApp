package com.nmichail.moviesapp.profile.presentation

sealed class ProfileEvent {
    object LoadProfile : ProfileEvent()
    object SignOut : ProfileEvent()
}