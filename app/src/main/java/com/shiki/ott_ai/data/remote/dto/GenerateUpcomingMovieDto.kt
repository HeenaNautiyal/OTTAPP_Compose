package com.shiki.ott_ai.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerateUpcomingMovieDto(
    @SerialName("dates")
    val dates: DatesDto? = null,
    @SerialName("page")
    val page: Int? = 1,
    @SerialName("results")
    val resultUpcoming: List<ResultUpcomingDto>? = emptyList(),
    @SerialName("total_pages")
    val total_pages: Int? = 1,
    @SerialName("total_results")
    val total_results: Int? = 0
)

@Serializable
data class DatesDto(
    @SerialName("maximum")
    val maximum: String? = null,
    @SerialName("minimum")
    val minimum: String? = null
)
