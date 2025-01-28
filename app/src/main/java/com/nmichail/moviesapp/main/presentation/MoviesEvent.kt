package com.nmichail.moviesapp.main.presentation

sealed class MoviesEvent {
    data class LoadMovies(val filter: String) : MoviesEvent()
}