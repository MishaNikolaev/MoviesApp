package com.nmichail.moviesapp.main.domain.usecases

import com.nmichail.moviesapp.main.domain.models.Movie
import com.nmichail.moviesapp.main.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCachedMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(filter: String): Flow<List<Movie>> {
        return repository.getCachedMovies(filter)
    }
}