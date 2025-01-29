package com.nmichail.moviesapp.main_details.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nmichail.moviesapp.main_details.domain.models.MovieDetails

@Entity
data class MovieDetailsEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val runtime: Int?,
    val voteAverage: Double,
    val releaseDate: String,
    val genres: String
) {
    fun toDomainModel(): MovieDetails {
        return MovieDetails(
            id = id,
            title = title,
            overview = overview,
            posterPath = posterPath,
            backdropPath = backdropPath,
            runtime = runtime,
            voteAverage = voteAverage,
            releaseDate = releaseDate,
            genres = genres.split(", ")
        )
    }
}