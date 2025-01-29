package com.nmichail.moviesapp.utils.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.nmichail.moviesapp.auth.presentation.AuthScreen
import com.nmichail.moviesapp.auth.presentation.AuthViewModel
import com.nmichail.moviesapp.main.presentation.MoviesViewModel
import com.nmichail.moviesapp.main.presentation.ui.GenreScreen
import com.nmichail.moviesapp.main.presentation.ui.MovieAppScreen
import com.nmichail.moviesapp.main_details.presentation.MovieDetailsViewModel
import com.nmichail.moviesapp.main_details.presentation.ui.MovieDetailsScreen

sealed class Screen(val route: String) {
    object Auth : Screen("auth_screen")
    object Main : Screen("main_screen")
}
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Auth.route
    ) {
        composable(Screen.Auth.route) {
            val viewModel: AuthViewModel = hiltViewModel()
            AuthScreen(
                viewModel = viewModel,
                onSessionCreated = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Auth.route) { inclusive = true }
                    }
                }
            )
        }

        navigation(
            route = Screen.Main.route,
            startDestination = "${Screen.Main.route}/home"
        ) {
            mainNavGraph(navController)
        }
    }
}

fun NavGraphBuilder.mainNavGraph(navController: androidx.navigation.NavController) {
    composable("${Screen.Main.route}/home") {
        val viewModel: MoviesViewModel = hiltViewModel()
        MovieAppScreen(
            viewModel = viewModel,
            onDetailsClick = { movieId ->
                navController.navigate("${Screen.Main.route}/details/$movieId")
            },
            onGenreClick = { genre ->
                navController.navigate("${Screen.Main.route}/genre_screen/${genre.lowercase()}")
            }
        )
    }

    composable("${Screen.Main.route}/genre_screen/{genre}") { backStackEntry ->
        val genre = backStackEntry.arguments?.getString("genre") ?: "all"
        val viewModel: MoviesViewModel = hiltViewModel()
        GenreScreen(
            genre = genre,
            viewModel = viewModel,
            onMovieClick = { movieId ->
                navController.navigate("${Screen.Main.route}/details/$movieId")
            }
        )
    }

    composable("${Screen.Main.route}/details/{movieId}") { backStackEntry ->
        val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull() ?: 0
        val viewModel: MovieDetailsViewModel = hiltViewModel()
        val lifecycleOwner = LocalLifecycleOwner.current
        MovieDetailsScreen(
            movieId = movieId,
            viewModel = viewModel,
            onBackClick = { navController.navigate("${Screen.Main.route}/home") },
        )
    }
}