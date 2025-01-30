package com.nmichail.moviesapp.search.data.dto

data class SearchResponseDto(
    val page: Int = 0,
    val results: List<SearchResultDto> = emptyList(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)