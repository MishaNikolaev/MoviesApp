package com.nmichail.moviesapp.main_details.domain.usecases

import com.nmichail.moviesapp.main_details.domain.models.MovieDetails
import com.nmichail.moviesapp.main_details.domain.repository.MovieDetailsRepository

class GetMovieDetailsUseCase(
    private val repository: MovieDetailsRepository
) {
    suspend operator fun invoke(movieId: Int): MovieDetails {
        return repository.getMovieDetails(movieId)
    }
}