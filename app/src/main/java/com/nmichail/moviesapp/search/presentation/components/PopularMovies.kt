package com.nmichail.moviesapp.search.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nmichail.moviesapp.search.domain.models.SearchResult
import com.nmichail.moviesapp.search.presentation.MoviesUiState


@Composable
fun PopularMovies(state: MoviesUiState, onMovieClick: (Int) -> Unit) {
    when (state) {
        is MoviesUiState.Idle -> {
            Text(
                text = "Discover Popular Movies and Actors:",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(8.dp)
            )
        }
        is MoviesUiState.Success -> {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(state.movies) { movie ->
                    MovieCard(
                        movie = SearchResult(
                            id = movie.id,
                            title = movie.title,
                            posterUrl = movie.posterPath,
                            overview = movie.overview,
                            mediaType = "movie",
                            releaseDate = movie.releaseDate
                        ),
                        onClick = { onMovieClick(movie.id) }
                    )
                }
            }
        }
        is MoviesUiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is MoviesUiState.Error -> {
            Text(
                text = "Error loading popular movies",
                color = Color.Red,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

