package com.shiki.ott_ai.domain.repository

import com.shiki.ott_ai.data.remote.dto.Genre
import com.shiki.ott_ai.domain.model.Movie
import com.shiki.ott_ai.domain.model.MovieDetail
import com.shiki.ott_ai.domain.model.ResultUpcoming
import com.shiki.ott_ai.data.remote.dto.Video
import com.shiki.ott_ai.domain.util.Result

interface MovieRepository {
    suspend fun getTrendingMovies(): Result<List<Movie>>
    suspend fun getMovieDetail(movieId: Int): Result<MovieDetail>
    suspend fun getFilterCategory(): Result<List<Genre>>
    suspend fun getUpcomingMovie(): Result<List<ResultUpcoming>>
    suspend fun getMovieClip(movieId: Int): Result<List<Video>>
}
