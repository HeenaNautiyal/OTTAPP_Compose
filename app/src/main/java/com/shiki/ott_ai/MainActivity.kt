package com.shiki.ott_ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shiki.ott_ai.domain.model.Movie
import com.shiki.ott_ai.domain.util.Result
import com.shiki.ott_ai.presentation.Movie.MovieDetailScreen
import com.shiki.ott_ai.presentation.ViewModel.Home.HomeScreen
import com.shiki.ott_ai.presentation.ViewModel.Home.HomeViewModel
import com.shiki.ott_ai.presentation.ViewModel.LoginCredential.SignInScreen
import com.shiki.ott_ai.presentation.ViewModel.LoginCredential.SignUpScreen
import com.shiki.ott_ai.ui.theme.OTT_AITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            )
        )
        setContent {
            OTT_AITheme {
                val navController = rememberNavController()
                val homeViewModel: HomeViewModel = hiltViewModel()

                NavHost(
                    navController = navController,
                    startDestination = "sign_in",
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable("sign_in") {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .paint(
                                    painterResource(id = R.drawable.image_background),
                                    contentScale = ContentScale.FillBounds
                                )
                        ) {
                            Scaffold(
                                modifier = Modifier.fillMaxSize(),
                                containerColor = Color.Transparent
                            ) { innerPadding ->
                                SignInScreen(
                                    modifier = Modifier.padding(innerPadding),
                                    viewModel = hiltViewModel(),
                                    onNavigateToHome = {
                                        navController.navigate("home") {
                                            popUpTo("sign_in") { inclusive = true }
                                        }
                                    },
                                    onNavigateToSignUp = {
                                        navController.navigate("sign_up")
                                    }
                                )
                            }
                        }
                    }
                    composable("sign_up") {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Scaffold(
                                modifier = Modifier.fillMaxSize(),
                                containerColor = Color.Transparent
                            ) { innerPadding ->
                                SignUpScreen(
                                    modifier = Modifier.padding(innerPadding),
                                    viewModel = hiltViewModel(),
                                    onNavigateToSignIn = {
                                        navController.popBackStack()   // or navigate("sign_in")
                                    },
                                    onNavigateToHome = {
                                        navController.navigate("home") {
                                            popUpTo(0) { inclusive = true }   // clear whole back stack
                                        }
                                    }
                                )
                            }
                        }
                    }
                    composable("home") {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .paint(
                                    painterResource(id = R.drawable.image_background),
                                    contentScale = ContentScale.FillBounds
                                )
                        ) {
                            Scaffold(
                                modifier = Modifier.fillMaxSize(),
                                containerColor = Color.Transparent
                            ) { innerPadding ->
                                HomeScreen(
                                    modifier = Modifier.padding(innerPadding),
                                    viewModel = homeViewModel,
                                    onMovieClick = { movieId ->
                                        navController.navigate("details/$movieId")
                                    },
                                )
                            }
                        }
                    }
                    composable(
                        route = "details/{movieId}",
                        arguments = listOf(navArgument("movieId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
                        val trendingState by homeViewModel.trendingMovies.collectAsState()
                        val trendingMovies = (trendingState as? Result.Success<List<Movie>>)?.data
                        val movie = trendingMovies?.find { it.id == movieId }?.let { upcoming ->
                                Movie(
                                    id = upcoming.id,
                                    title = upcoming.title,
                                    overview = upcoming.overview,
                                    posterPath = upcoming.posterPath,
                                    voteAverage = upcoming.voteAverage,
                                    backdropPath = upcoming.backdropPath,
                                    releaseDate = upcoming.releaseDate
                                )
                            }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .paint(
                                    painterResource(id = R.drawable.image_background),
                                    contentScale = ContentScale.FillBounds
                                )
                        ) {
                            if (movie != null) {
                                MovieDetailScreen(
                                    modifier = Modifier.fillMaxSize(),
                                    movie = movieId,
                                    onDismiss = { navController.popBackStack() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
