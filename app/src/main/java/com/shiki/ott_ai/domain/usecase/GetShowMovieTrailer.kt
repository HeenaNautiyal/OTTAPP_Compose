package com.shiki.ott_ai.domain.usecase

import com.shiki.ott_ai.data.remote.dto.Video
import com.shiki.ott_ai.domain.repository.MovieRepository
import com.shiki.ott_ai.domain.util.Result
import javax.inject.Inject

class GetShowMovieTrailer @Inject constructor(
    val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Result<List<Video>> {
        return movieRepository.getMovieClip(movieId)
    }
}
