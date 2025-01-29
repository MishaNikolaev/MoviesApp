package com.nmichail.moviesapp.main_details.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun StarRating(rating: Double) {
    val maxStars = 5
    val filledStars = (rating / 2).toInt()
    val unfilledStars = maxStars - filledStars

    Row {
        repeat(filledStars) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Filled Star",
                tint = Color(0xFFF2C94C)
            )
        }
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Unfilled Star",
                tint = Color.LightGray
            )
        }
    }
}