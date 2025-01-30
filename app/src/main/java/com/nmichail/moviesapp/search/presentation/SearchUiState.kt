package com.nmichail.moviesapp.search.presentation

import com.nmichail.moviesapp.main.domain.models.Movie
import com.nmichail.moviesapp.search.domain.models.SearchResult

sealed class SearchUiState {
    object Idle : SearchUiState()
    object Loading : SearchUiState()
    data class Success(val results: List<SearchResult>) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}

sealed class MoviesUiState {
    object Idle : MoviesUiState()
    object Loading : MoviesUiState()
    data class Success(val movies: List<Movie>) : MoviesUiState()
    data class Error(val message: String) : MoviesUiState()
}
