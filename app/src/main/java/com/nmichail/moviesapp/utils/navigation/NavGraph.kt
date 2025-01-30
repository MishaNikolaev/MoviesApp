package com.nmichail.moviesapp.utils.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.nmichail.moviesapp.R
import com.nmichail.moviesapp.auth.presentation.AuthScreen
import com.nmichail.moviesapp.auth.presentation.AuthViewModel
import com.nmichail.moviesapp.main.presentation.MoviesViewModel
import com.nmichail.moviesapp.main.presentation.ui.GenreScreen
import com.nmichail.moviesapp.main.presentation.ui.MovieAppScreen
import com.nmichail.moviesapp.main_details.presentation.MovieDetailsViewModel
import com.nmichail.moviesapp.main_details.presentation.ui.MovieDetailsScreen
import com.nmichail.moviesapp.profile.presentation.ProfileScreen
import com.nmichail.moviesapp.search.presentation.SearchScreen
import com.nmichail.moviesapp.search.presentation.SearchViewModel

sealed class Screen(val route: String, val icon: Int, val label: String) {
    object Auth : Screen("auth_screen", 0, "Auth")
    object Home : Screen("home_screen", R.drawable.icons8_home_96, "Home")
    object Search : Screen("search_screen", R.drawable.search, "Search")
    object Profile : Screen("profile_screen", R.drawable.profile, "Profile")
    object Details : Screen("details_screen/{movieId}", 0, "Details")
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(Screen.Home, Screen.Search, Screen.Profile)
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(painterResource(id = screen.icon), contentDescription = screen.label) },
                label = { Text(text = screen.label) },
                selected = currentRoute == screen.route,
                alwaysShowLabel = true,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(Screen.Home.route) { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                },
                modifier = Modifier.size(25.dp),
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFF2C94C),
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}

fun NavGraphBuilder.addBottomBarScreens(navController: NavHostController) {
    composable(Screen.Home.route) {
        val viewModel: MoviesViewModel = hiltViewModel()

        MovieAppScreen(
            viewModel = viewModel,
            onDetailsClick = { movieId ->
                navController.navigate("details_screen/$movieId")
            },
            onGenreClick = { genre ->
                navController.navigate("genre_screen/${genre.lowercase().replace(" ", "_")}") // Переход на экран с жанрами
            }
        )
    }

    composable(Screen.Search.route) {
        val viewModel: SearchViewModel = hiltViewModel()
        SearchScreen(
            viewModel,
            onMovieClick = { movieId ->
                navController.navigate("details_screen/$movieId")
            }
        )
    }


    composable(Screen.Profile.route) {
        ProfileScreen(
        )
    }
}
@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Auth.route
    ) {
        composable(Screen.Auth.route) {
            val viewModel: AuthViewModel = hiltViewModel()
            AuthScreen(
                viewModel = viewModel,
                onSessionCreated = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Auth.route) { inclusive = true }
                    }
                }
            )
        }

        navigation(
            startDestination = Screen.Home.route,
            route = "main_with_bottom_bar"
        ) {
            addBottomBarScreens(navController)
        }

        composable("genre_screen/{genre}") { backStackEntry ->
            val genre = backStackEntry.arguments?.getString("genre") ?: "all"
            GenreScreen(
                genre = genre,
                viewModel = hiltViewModel(),
                onMovieClick = { movieId ->
                    navController.navigate("details_screen/$movieId")
                }
            )
        }

        composable(Screen.Details.route) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull() ?: 0
            val detailsViewModel: MovieDetailsViewModel = hiltViewModel()

            MovieDetailsScreen(
                movieId = movieId,
                viewModel = detailsViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry.value?.destination?.route

            if (currentRoute in listOf(Screen.Home.route, Screen.Search.route, Screen.Profile.route)) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    composable("${Screen.Home.route}/home") {
        MovieAppScreen(
            viewModel = hiltViewModel(),
            onDetailsClick = { movieId ->
                navController.navigate("${Screen.Home.route}/details/$movieId")
            },
            onGenreClick = { genre ->
                navController.navigate("${Screen.Home.route}/genre_screen/${genre.lowercase().replace(" ", "_")}")
            }
        )
    }

    composable("${Screen.Home.route}/genre_screen/{genre}") { backStackEntry ->
        val genre = backStackEntry.arguments?.getString("genre") ?: "all"
        GenreScreen(
            genre = genre,
            viewModel = hiltViewModel(),
            onMovieClick = { movieId ->
                navController.navigate("${Screen.Home.route}/details/$movieId")
            }
        )
    }

    composable("${Screen.Home.route}/details/{movieId}") { backStackEntry ->
        val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
        if (movieId == null) {
            navController.popBackStack()
        } else {
            MovieDetailsScreen(
                movieId = movieId,
                viewModel = hiltViewModel(),
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
