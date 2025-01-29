package com.nmichail.moviesapp.main_details.data.repository

import android.util.Log
import com.nmichail.moviesapp.main_details.data.dto.toDomain
import com.nmichail.moviesapp.main_details.data.local.MovieDetailsDao
import com.nmichail.moviesapp.main_details.data.local.MovieDetailsEntity
import com.nmichail.moviesapp.main_details.data.remote.MovieDetailsApi
import com.nmichail.moviesapp.main_details.domain.models.MovieDetails
import com.nmichail.moviesapp.main_details.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDetailsRepositoryImpl(
    private val api: MovieDetailsApi,
    private val dao: MovieDetailsDao
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return withContext(Dispatchers.IO) {
            val localData = dao.getMovieDetails(movieId)?.toDomainModel()
            if (localData != null) {
                localData
            } else {
                val response = api.getMovieDetails(movieId)
                val entity = MovieDetailsEntity(
                    id = response.id,
                    title = response.title,
                    overview = response.overview,
                    posterPath = response.poster_path,
                    backdropPath = response.backdrop_path,
                    runtime = response.runtime,
                    voteAverage = response.vote_average,
                    releaseDate = response.release_date,
                    genres = response.genres.joinToString(", ") { it.name }
                )
                dao.insertMovieDetails(entity)
                response.toDomain()
            }
        }
    }
}