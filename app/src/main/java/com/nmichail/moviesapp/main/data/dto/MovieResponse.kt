package com.nmichail.moviesapp.main.data.dto

data class MovieResponse(
    val dates: Dates? = null,
    val page: Int,
    val results: List<MovieDto>
)

data class Dates(
    val maximum: String,
    val minimum: String
)