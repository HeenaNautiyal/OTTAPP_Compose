package com.shiki.ott_ai.domain.usecase

import com.shiki.ott_ai.domain.model.MovieDetail
import com.shiki.ott_ai.domain.repository.MovieRepository
import com.shiki.ott_ai.domain.util.Result
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Result<MovieDetail> {
        return repository.getMovieDetail(movieId)
    }
}
