package com.nmichail.moviesapp.main.presentation

import com.nmichail.moviesapp.main.domain.models.Movie

data class MoviesState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val movies: List<Movie> = emptyList(),
    val nowPlaying: List<Movie> = emptyList(),
    val popular: List<Movie> = emptyList(),
    val topRated: List<Movie> = emptyList(),
    val upcoming: List<Movie> = emptyList(),
)