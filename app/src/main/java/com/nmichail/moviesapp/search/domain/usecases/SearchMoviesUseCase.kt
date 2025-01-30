package com.nmichail.moviesapp.search.domain.usecases

import com.nmichail.moviesapp.search.domain.models.SearchResult
import com.nmichail.moviesapp.search.domain.repository.SearchRepository
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(query: String): List<SearchResult> {
        val response = repository.search(query)
        return response.results.map {
            SearchResult(
                id = it.id,
                title = it.title ?: it.name,
                mediaType = it.media_type,
                overview = it.overview,
                posterUrl = it.poster_path,
                releaseDate = it.release_date
            )
        }
    }
}