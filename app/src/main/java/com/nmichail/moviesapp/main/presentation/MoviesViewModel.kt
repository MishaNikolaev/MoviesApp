package com.nmichail.moviesapp.main.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nmichail.moviesapp.main.domain.repository.MovieRepository
import com.nmichail.moviesapp.main.domain.usecases.GetCachedMoviesUseCase
import com.nmichail.moviesapp.main.domain.usecases.GetMoviesUseCase
import com.nmichail.moviesapp.search.presentation.MoviesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getCachedMoviesUseCase: GetCachedMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MoviesState())
    val state: StateFlow<MoviesState> get() = _state

    fun onEvent(event: MoviesEvent) {
        when (event) {
            is MoviesEvent.LoadMovies -> {
                val validFilter = when (event.filter) {
                    "now_playing", "popular", "top_rated", "upcoming" -> event.filter
                    else -> throw IllegalArgumentException("Invalid filter")
                }

                loadMovies(validFilter)
            }
        }
    }

    private fun loadMovies(filter: String) {
        viewModelScope.launch {
            getCachedMoviesUseCase(filter).collect { cachedMovies ->
                if (cachedMovies.isNotEmpty()) {
                    _state.value = _state.value.copy(movies = cachedMovies, isLoading = false)
                } else {
                    try {
                        val movies = getMoviesUseCase(filter, 1)
                        _state.value = _state.value.copy(movies = movies, isLoading = false)
                    } catch (e: Exception) {
                        _state.value = _state.value.copy(isLoading = false, error = e.localizedMessage)
                    }
                }
            }
        }
    }

    init {
        loadInitialMovies()
    }

    private fun loadInitialMovies() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val nowPlaying = getMoviesUseCase("now_playing", 1)
                val popular = getMoviesUseCase("popular", 1)
                val topRated = getMoviesUseCase("top_rated", 1)
                val upcoming = getMoviesUseCase("upcoming", 1)

                Log.d("MoviesViewModel", "NowPlaying: ${nowPlaying.size}, Popular: ${popular.size}")

                val allMovies = nowPlaying + popular + topRated + upcoming
                _state.value = _state.value.copy(
                    nowPlaying = nowPlaying,
                    popular = popular,
                    topRated = topRated,
                    upcoming = upcoming,
                    movies = allMovies,
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.localizedMessage
                )
                Log.e("MoviesViewModel", "Error loading movies: ${e.localizedMessage}")
            }
        }
    }
    private val _popularMoviesUiState = MutableStateFlow<MoviesUiState>(MoviesUiState.Idle)
    val popularMoviesUiState: StateFlow<MoviesUiState> = _popularMoviesUiState

    fun loadPopularMovies() {
        viewModelScope.launch {
            _popularMoviesUiState.value = MoviesUiState.Loading
            try {
                val movies = getMoviesUseCase("popular", 1)
                _popularMoviesUiState.value = MoviesUiState.Success(movies)
            } catch (e: Exception) {
                _popularMoviesUiState.value = MoviesUiState.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }
}
