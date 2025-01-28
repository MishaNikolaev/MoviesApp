package com.nmichail.moviesapp.main.data.repository

import com.nmichail.moviesapp.main.data.dto.MovieResponse
import com.nmichail.moviesapp.main.data.local.MovieDao
import com.nmichail.moviesapp.main.data.local.MovieEntity
import com.nmichail.moviesapp.main.data.remote.MovieApiService
import com.nmichail.moviesapp.main.domain.models.Movie
import com.nmichail.moviesapp.main.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: MovieApiService,
    private val dao: MovieDao
) : MovieRepository {

    override suspend fun getMovies(filter: String, page: Int): MovieResponse {
        val response = apiService.getMoviesByFilter(filter, page)
        dao.deleteMoviesByGenre(filter)
        dao.insertMovies(response.results.map { dto ->
            MovieEntity(
                id = dto.id,
                title = dto.title,
                posterPath = dto.poster_path,
                overview = dto.overview,
                releaseDate = dto.release_date,
                voteAverage = dto.vote_average,
                genre = filter
            )
        })
        return response
    }

    override suspend fun getCachedMovies(filter: String): Flow<List<Movie>> {
        return dao.getMoviesByGenre(filter).map { entities ->
            entities.map { entity ->
                Movie(
                    id = entity.id,
                    title = entity.title,
                    posterPath = entity.posterPath,
                    overview = entity.overview,
                    releaseDate = entity.releaseDate,
                    voteAverage = entity.voteAverage
                )
            }
        }
    }
}