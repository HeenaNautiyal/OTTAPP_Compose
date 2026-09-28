package com.shiki.ott_ai.presentation.ViewModel.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shiki.ott_ai.data.remote.dto.Genre
import com.shiki.ott_ai.domain.model.Movie
import com.shiki.ott_ai.domain.model.MovieDetail
import com.shiki.ott_ai.domain.model.ResultUpcoming
import com.shiki.ott_ai.data.remote.dto.Video
import com.shiki.ott_ai.domain.usecase.GetFilterCategoryUseCase
import com.shiki.ott_ai.domain.usecase.GetMovieDetailUseCase
import com.shiki.ott_ai.domain.usecase.GetShowMovieTrailer
import com.shiki.ott_ai.domain.usecase.GetTrendingMoviesUseCase
import com.shiki.ott_ai.domain.usecase.GetUpcomingMovieUseCase
import com.shiki.ott_ai.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val  getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val getFilterCategoryUseCase: GetFilterCategoryUseCase,
    private val getUpcomingMovieUseCase: GetUpcomingMovieUseCase,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getShowMovieTrailer: GetShowMovieTrailer
) : ViewModel() {

    private val _trendingMovies = MutableStateFlow<Result<List<Movie>>?>(null)
    val trendingMovies: StateFlow<Result<List<Movie>>?> = _trendingMovies.asStateFlow()

    private val _filterGener = MutableStateFlow<Result<List<Genre>>?>(null)
    val filterGener: StateFlow<Result<List<Genre>>?> = _filterGener.asStateFlow()

    private val _upcominMovie = MutableStateFlow<Result<List<ResultUpcoming>>?>(null)
    val upcominMovie: StateFlow<Result<List<ResultUpcoming>>?> = _upcominMovie.asStateFlow()

    private val _MovieDetails = MutableStateFlow<Result<MovieDetail>?>(null)
    val MovieDetails: StateFlow<Result<MovieDetail>?> = _MovieDetails.asStateFlow()

    private val _MovieClips = MutableStateFlow<Result<List<Video>>?>(null)
    val MovieClips: StateFlow<Result<List<Video>>?> = _MovieClips.asStateFlow()

    private val _trailerKey = MutableStateFlow<String?>(null)
    val trailerKey: StateFlow<String?> = _trailerKey.asStateFlow()

    init {
        fetchTrendingMovies()
    }

    fun fetchTrendingMovies() {
        viewModelScope.launch {
            _trendingMovies.value = Result.Loading
            _filterGener.value = Result.Loading
            _upcominMovie.value = Result.Loading

            val result = async { getTrendingMoviesUseCase() }
            val resultCategory = async { getFilterCategoryUseCase() }
            val resultUpcominMovies = async { getUpcomingMovieUseCase() }

            _trendingMovies.value = result.await()
            _filterGener.value = resultCategory.await()
            _upcominMovie.value = resultUpcominMovies.await()
        }
    }

    fun fetchMovieDetails(movieId:Int){
        viewModelScope.launch {
            _MovieDetails.value = Result.Loading
            val resultData = getMovieDetailUseCase(movieId)
            _MovieDetails.value = resultData
        }
    }

    fun showMovieClip(movieId: Int){
        viewModelScope.launch {
            _MovieClips.value = Result.Loading
            val result = getShowMovieTrailer(movieId)
            _MovieClips.value = result

            if (result is Result.Success) {
                val bestTrailer = result.data
                    .filter { it.site.equals("YouTube", ignoreCase = true) }
                    .filter { it.type == "Trailer" || it.type == "Teaser" }
                    .sortedByDescending { it.official }
                    .firstOrNull()

                _trailerKey.value = bestTrailer?.key
            }
        }
    }
}
