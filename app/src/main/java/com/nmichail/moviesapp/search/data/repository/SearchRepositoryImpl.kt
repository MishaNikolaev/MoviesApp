package com.nmichail.moviesapp.search.data.repository

import com.nmichail.moviesapp.search.data.dto.SearchResponseDto
import com.nmichail.moviesapp.search.data.remote.SearchApi
import com.nmichail.moviesapp.search.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi
) : SearchRepository {
    override suspend fun search(query: String): SearchResponseDto {
        return searchApi.searchMulti(query)
    }
}