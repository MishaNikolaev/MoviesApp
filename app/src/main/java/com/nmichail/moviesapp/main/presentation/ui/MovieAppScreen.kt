package com.nmichail.moviesapp.main.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nmichail.moviesapp.R
import com.nmichail.moviesapp.main.presentation.MoviesViewModel


@Composable
fun MovieAppScreen(
    viewModel: MoviesViewModel = hiltViewModel(),
    onDetailsClick: (Int) -> Unit,
    onGenreClick: (String) -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val filters = listOf("All", "Now Playing", "Popular", "Top Rated", "Upcoming")
    var selectedFilter by remember { mutableStateOf("All") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.osd2),
                contentDescription = "Header Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Black, Color.Transparent)
                        )
                    )
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            ) {
                items(filters) { filter ->
                    Text(
                        text = filter,
                        color = if (selectedFilter == filter) Color.Black else Color.White.copy(
                            alpha = 0.8f
                        ),
                        modifier = Modifier
                            .background(
                                if (selectedFilter == filter) Color.White else Color.Gray.copy(alpha = 0.6f),
                                shape = CircleShape
                            )
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                            .clickable {
                                selectedFilter = filter
                                if (filter != "All") {
                                    onGenreClick(filter.lowercase().replace(" ", "_"))
                                }
                            }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* TODO: Implement */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2C94C)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "My List")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (selectedFilter == "All") {
            MovieCategorySection(
                title = "Now Playing",
                movies = state.nowPlaying.take(3),
                onMovieClick = onDetailsClick,
                onSeeMoreClick = { onGenreClick("now_playing") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            MovieCategorySection(
                title = "Popular",
                movies = state.popular.take(3),
                onMovieClick = onDetailsClick,
                onSeeMoreClick = { onGenreClick("popular") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            MovieCategorySection(
                title = "Top Rated",
                movies = state.topRated.take(3),
                onMovieClick = onDetailsClick,
                onSeeMoreClick = { onGenreClick("top_rated") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            MovieCategorySection(
                title = "Upcoming",
                movies = state.upcoming.take(3),
                onMovieClick = onDetailsClick,
                onSeeMoreClick = { onGenreClick("upcoming") }
            )
        } else {
            val movies = when (selectedFilter) {
                "Now Playing" -> state.nowPlaying
                "Popular" -> state.popular
                "Top Rated" -> state.topRated
                "Upcoming" -> state.upcoming
                else -> emptyList()
            }

            MovieCategorySection(
                title = selectedFilter,
                movies = movies.take(3),
                onMovieClick = onDetailsClick,
                onSeeMoreClick = { onGenreClick(selectedFilter.lowercase().replace(" ", "_")) }
            )
        }
        Spacer(modifier = Modifier.height(80.dp))
    }
}
