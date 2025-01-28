package com.nmichail.moviesapp.main.data.remote

import com.nmichail.moviesapp.main.data.dto.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/{filter}")
    suspend fun getMoviesByFilter(
        @Path("filter") filter: String,
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US"
    ): MovieResponse
}