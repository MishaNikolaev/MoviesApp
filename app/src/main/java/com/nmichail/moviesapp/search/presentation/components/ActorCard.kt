package com.nmichail.moviesapp.search.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.nmichail.moviesapp.R
import com.nmichail.moviesapp.search.domain.models.SearchResult

@Composable
fun ActorCard(actor: SearchResult) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
            .padding(12.dp)
    ) {
        val imageUrl = "https://image.tmdb.org/t/p/w200${actor.posterUrl}"
        Image(
            painter = rememberAsyncImagePainter(
                model = imageUrl,
                error = painterResource(R.drawable.profile),
                placeholder = painterResource(R.drawable.profile)
            ),
            contentDescription = actor.title ?: "Actor Image",
            modifier = Modifier
                .size(80.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = actor.title ?: "Unknown Name",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}