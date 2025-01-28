package com.nmichail.moviesapp.main.domain.repository

import com.nmichail.moviesapp.main.data.dto.MovieResponse
import com.nmichail.moviesapp.main.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovies(filter: String, page: Int): MovieResponse

    suspend fun getCachedMovies(filter: String): Flow<List<Movie>>

}