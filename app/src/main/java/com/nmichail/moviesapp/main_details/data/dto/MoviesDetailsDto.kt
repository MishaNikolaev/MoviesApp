package com.nmichail.moviesapp.main_details.data.dto

import android.util.Log
import com.nmichail.moviesapp.main_details.domain.models.MovieDetails

data class MovieDetailsDto(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val runtime: Int?,
    val vote_average: Double,
    val release_date: String,
    val genres: List<GenreDto>,
) {

    fun toDomainModel(): MovieDetails {

        return MovieDetails(
            id = id,
            title = title,
            overview = overview,
            posterPath = poster_path,
            backdropPath = backdrop_path,
            runtime = runtime,
            voteAverage = vote_average,
            releaseDate = release_date,
            genres = genres.map { it.name },
        )
    }
}

fun MovieDetailsDto.toDomain(): MovieDetails {

    return MovieDetails(
        id = id,
        title = title,
        overview = overview,
        posterPath = poster_path,
        backdropPath = backdrop_path,
        runtime = runtime,
        voteAverage = vote_average,
        releaseDate = release_date,
        genres = genres.map { it.name },
    )
}