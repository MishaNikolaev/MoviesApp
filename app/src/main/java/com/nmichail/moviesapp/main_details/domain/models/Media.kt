package com.nmichail.moviesapp.main_details.domain.models

data class Media(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val mediaType: String,
    val isLiked: Boolean = false,
    val isBookmarked: Boolean = false
)