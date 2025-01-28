package com.nmichail.moviesapp.main.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterPath: String?,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val genre: String
)