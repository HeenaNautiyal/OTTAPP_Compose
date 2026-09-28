package com.shiki.ott_ai.domain.usecase

import com.shiki.ott_ai.domain.model.Movie
import com.shiki.ott_ai.domain.repository.MovieRepository
import com.shiki.ott_ai.domain.util.Result
import javax.inject.Inject

class GetTrendingMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): Result<List<Movie>>{
        return movieRepository.getTrendingMovies()
    }
}