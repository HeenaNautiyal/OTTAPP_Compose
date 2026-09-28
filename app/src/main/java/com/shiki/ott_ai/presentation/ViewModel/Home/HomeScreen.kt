package com.shiki.ott_ai.presentation.ViewModel.Home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.shiki.ott_ai.R
import com.shiki.ott_ai.domain.model.ResultUpcoming
import com.shiki.ott_ai.data.remote.dto.Genre
import com.shiki.ott_ai.domain.model.Movie
import com.shiki.ott_ai.domain.util.Result
import com.shiki.ott_ai.presentation.Movie.FilterMovieCriteria
import com.shiki.ott_ai.presentation.Movie.MovieRow
import com.shiki.ott_ai.presentation.MyTopAppBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onMovieClick: (Int)-> Unit
) {
    val trendingMoviesState by viewModel.trendingMovies.collectAsStateWithLifecycle()
    val filterGenerState by viewModel.filterGener.collectAsStateWithLifecycle()
    val upcomingMovieState by viewModel.upcominMovie.collectAsStateWithLifecycle()

    val BebasNeue = FontFamily(
        Font(R.font.bebas_neue, FontWeight.Normal)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
          MyTopAppBar( "OTT Apps",BebasNeue)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()
            .padding(innerPadding)
        )
        {
            // Main Loading State: If any of the essential data is still loading
            if (trendingMoviesState is Result.Loading || 
                filterGenerState is Result.Loading || 
                upcomingMovieState is Result.Loading || 
                trendingMoviesState == null) {
                
                CircularProgressIndicator(
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (trendingMoviesState is Result.Error) {
                Text(
                    text = (trendingMoviesState as Result.Error).message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (trendingMoviesState is Result.Success) {
                val movies = (trendingMoviesState as Result.Success<List<Movie>>).data
                val genres = (filterGenerState as? Result.Success<List<Genre>>)?.data ?: emptyList()
                val upcoming = (upcomingMovieState as? Result.Success<List<ResultUpcoming>>)?.data ?: emptyList()

                HomeContent(
                    movies = movies,
                    filterList = genres,
                    upcomingMovies = upcoming,
                    fontfamily = BebasNeue,
                    onMovieClick = onMovieClick,
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    movies: List<Movie>,
    filterList: List<Genre>,
    upcomingMovies: List<ResultUpcoming>,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    fontfamily: FontFamily,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        item {
            FilterMovieCriteria(filterList)
        }
        item {
            MovieRow(
                title = "Trending Movies",
                movies = movies,
                onMovieClick = onMovieClick,
                fontfamily = fontfamily
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Recent Watched",
                    modifier = Modifier.weight(0.7f),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontfamily
                )

                Text(
                    text = "View all",
                    modifier = Modifier
                        .weight(0.3f)
                        .clickable { /* View all click */ },
                    color = Color.White,
                    textAlign = TextAlign.End
                )
            }
        }
        if (upcomingMovies.isNotEmpty()) {
            item {
                Text(
                    text = "Upcoming Movies",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontfamily
                )
            }

            item {
                Column(modifier = Modifier.padding(8.dp)) {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(upcomingMovies) { upcoming ->
                            Column(
                                modifier = Modifier
                                    .width(130.dp)
                                    .clickable { onMovieClick(upcoming.id) },
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w500${upcoming.posterPath}",
                                    contentDescription = upcoming.title,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(2f / 3f)
                                        .clip(RoundedCornerShape(8.dp)),
                                    alignment = Alignment.Center
                                )

                                Text(
                                    text = upcoming.title,
                                    color = Color.White,
                                    fontSize = 13.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
