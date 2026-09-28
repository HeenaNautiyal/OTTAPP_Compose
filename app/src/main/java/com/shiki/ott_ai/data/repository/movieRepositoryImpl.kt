package com.shiki.ott_ai.data.repository

import com.shiki.ott_ai.data.mapper.toDomain
import com.shiki.ott_ai.data.remote.TmdbApiService
import com.shiki.ott_ai.data.remote.dto.Genre
import com.shiki.ott_ai.domain.model.Movie
import com.shiki.ott_ai.domain.model.MovieDetail
import com.shiki.ott_ai.domain.model.ResultUpcoming
import com.shiki.ott_ai.data.remote.dto.Video
import com.shiki.ott_ai.domain.repository.MovieRepository
import com.shiki.ott_ai.domain.util.Result
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: TmdbApiService
) : MovieRepository {

    override suspend fun getTrendingMovies(): Result<List<Movie>> {
        return try {
            val response = apiService.getTrendingMovies()
            val movies = response.results?.map { it.toDomain() } ?: emptyList()
            Result.Success(movies)
        } catch (e: Exception) {
            Result.Error(message = e.message ?: "Unknown error", exception = e)
        }
    }

    override suspend fun getMovieDetail(movieId: Int): Result<MovieDetail> {
        return try {
            val response = apiService.getMovieDetail(movieId)
            Result.Success(response.toDomain())
        } catch (e: Exception) {
            Result.Error(message = e.message ?: "Unknown error", exception = e)
        }
    }

    override suspend fun getFilterCategory(): Result<List<Genre>> {
        return try {
            val response = apiService.getFilterCategories()
            val genres = response.genres.map { it.toDomain() }
            Result.Success(genres)
        } catch (e: Exception) {
            Result.Error(message = e.message ?: "Unknown error", exception = e)
        }
    }

    override suspend fun getUpcomingMovie(): Result<List<ResultUpcoming>> {
        return try {
            val response = apiService.getUpcomingMovies()
            val upcomingMovies = response.resultUpcoming?.map { it.toDomain() } ?: emptyList()
            Result.Success(upcomingMovies)
        } catch (e: Exception) {
            Result.Error(message = e.message ?: "Unknown error", exception = e)
        }
    }

    override suspend fun getMovieClip(movieId: Int): Result<List<Video>> {
        return try {
            val response = apiService.showMovieClip(movieId)
            val videos = response.results?.map { it.toDomain() }?:emptyList()
            Result.Success(videos)
        } catch (e: Exception) {
            Result.Error(message = e.message ?: "Unknown error", exception = e)
        }
    }
}
