package com.nmichail.moviesapp.main.domain.usecases

import com.nmichail.moviesapp.main.domain.models.Movie
import com.nmichail.moviesapp.main.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(filter: String, page: Int): List<Movie> {
        return repository.getMovies(filter, page).results.map { dto ->
            Movie(
                id = dto.id,
                title = dto.title,
                posterPath = dto.poster_path,
                overview = dto.overview,
                releaseDate = dto.release_date,
                voteAverage = dto.vote_average
            )
        }
    }
}