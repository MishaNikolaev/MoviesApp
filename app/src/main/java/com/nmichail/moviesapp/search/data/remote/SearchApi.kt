package com.nmichail.moviesapp.search.data.remote

import com.nmichail.moviesapp.search.data.dto.SearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("search/multi")
    suspend fun searchMulti(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): SearchResponseDto
}