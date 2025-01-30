package com.nmichail.moviesapp.search.domain.repository

import com.nmichail.moviesapp.search.data.dto.SearchResponseDto

interface SearchRepository {

    suspend fun search(query: String): SearchResponseDto

}