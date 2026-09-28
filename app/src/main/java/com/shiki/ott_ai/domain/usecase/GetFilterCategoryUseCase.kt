package com.shiki.ott_ai.domain.usecase

import com.shiki.ott_ai.data.remote.dto.Genre
import com.shiki.ott_ai.domain.repository.MovieRepository
import javax.inject.Inject
import com.shiki.ott_ai.domain.util.Result

class GetFilterCategoryUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): Result<List<Genre>> {
        return movieRepository.getFilterCategory()
    }
}
