package com.nmichail.moviesapp.main_details.domain.repository

import com.nmichail.moviesapp.main_details.domain.models.MovieDetails

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int): MovieDetails
}