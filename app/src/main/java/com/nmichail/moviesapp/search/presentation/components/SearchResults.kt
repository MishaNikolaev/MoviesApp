package com.nmichail.moviesapp.search.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nmichail.moviesapp.search.presentation.SearchUiState


@Composable
fun SearchResults(state: SearchUiState, onMovieClick: (Int) -> Unit) {
    when (state) {
        is SearchUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is SearchUiState.Success -> {
            CategorizedResults(results = state.results, onMovieClick = onMovieClick)
        }
        is SearchUiState.Error -> {
            Text(
                text = state.message,
                color = Color.Red,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        else -> {}
    }
}