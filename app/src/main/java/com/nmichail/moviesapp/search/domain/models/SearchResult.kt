package com.nmichail.moviesapp.search.domain.models

data class SearchResult(
    val id: Int,
    val title: String?,
    val mediaType: String?,
    val overview: String?,
    val posterUrl: String?,
    val releaseDate: String?
)