package com.nmichail.moviesapp.search.data.dto

data class SearchResultDto(
    val id: Int,
    val title: String?,
    val name: String?,
    val media_type: String?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val popularity: Float
)