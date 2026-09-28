package com.shiki.ott_ai.data.remote

import com.shiki.ott_ai.BuildConfig
import com.shiki.ott_ai.data.remote.dto.GenerateUpcomingMovieDto
import com.shiki.ott_ai.data.remote.dto.GenresResponseDto
import com.shiki.ott_ai.data.remote.dto.MovieClipDto
import com.shiki.ott_ai.data.remote.dto.MovieResponseDto
import com.shiki.ott_ai.data.remote.dto.MovieDetailDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class TmdbApiService(private val client: HttpClient) {

    private val baseUrl = "https://api.themoviedb.org/3"
    private val api_key = BuildConfig.TMDB_API_KEY

    suspend fun getTrendingMovies(): MovieResponseDto {
        return client.get("$baseUrl/trending/movie/week") {
            parameter("api_key", api_key)
        }.body()
    }

    suspend fun getMovieDetail(id: Int): MovieDetailDto {
        return client.get("$baseUrl/movie/$id") {
            parameter("api_key", api_key)
            parameter("append_to_response","videos,credits")
        }.body()
    }

    suspend fun getFilterCategories(language: String = "en"): GenresResponseDto {
        return client.get("$baseUrl/genre/movie/list") {
            parameter("api_key", api_key)
            parameter("language", language)
        }.body()
    }

    suspend fun getUpcomingMovies(page: Int = 1): GenerateUpcomingMovieDto {
        return client.get("$baseUrl/movie/upcoming") {
            parameter("api_key", api_key)
            parameter("page", page)
        }.body()
    }

    suspend fun showMovieClip(movieId: Int): MovieClipDto {
        return client.get("$baseUrl/movie/$movieId/videos") {
            parameter("api_key", api_key)
            parameter("movie_id", movieId)
        }.body()
    }
}
