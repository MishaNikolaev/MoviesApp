package com.nmichail.moviesapp.main_details.domain.models


data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val runtime: Int?,
    val voteAverage: Double,
    val releaseDate: String,
    val genres: List<String>,
)