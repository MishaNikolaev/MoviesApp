package com.nmichail.moviesapp.profile.data.dto

data class ProfileDto(
    val avatar: AvatarDto,
    val id: Long,
    val name: String?,
    val username: String,
    val iso_639_1: String?,
    val iso_3166_1: String?,
)
