package com.nmichail.moviesapp.main_details.presentation

import com.nmichail.moviesapp.main_details.domain.models.MovieDetails

data class MovieDetailsState(
    val movieDetails: MovieDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)