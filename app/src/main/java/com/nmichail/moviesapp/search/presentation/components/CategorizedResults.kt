package com.nmichail.moviesapp.search.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nmichail.moviesapp.search.domain.models.SearchResult

@Composable
fun CategorizedResults(results: List<SearchResult>, onMovieClick: (Int) -> Unit) {
    val movies = results.filter { it.mediaType == "movie" }
    val actors = results.filter { it.mediaType == "person" }

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        if (movies.isNotEmpty()) {
            item {
                Text(
                    text = "Movies",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 8.dp, top = 16.dp)
                )
            }
            items(movies) { movie ->
                MovieCard(movie, onClick = { onMovieClick(movie.id) })
            }
        }

        if (actors.isNotEmpty()) {
            item {
                Text(
                    text = "Actors",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 8.dp, top = 16.dp)
                )
            }
            items(actors) { actor ->
                ActorCard(actor)
            }
        }

        item {
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}