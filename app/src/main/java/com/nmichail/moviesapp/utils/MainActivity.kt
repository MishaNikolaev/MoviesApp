package com.nmichail.moviesapp.utils

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nmichail.moviesapp.auth.presentation.AuthScreen
import com.nmichail.moviesapp.utils.navigation.NavGraph
import com.nmichail.moviesapp.utils.ui.theme.MoviesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesAppTheme {
                NavGraph()
            }
        }
    }
}