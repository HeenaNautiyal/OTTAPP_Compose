package com.shiki.ott_ai.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieResponseDto(
    @SerialName("page")
    val page: Int? = null,
    @SerialName("results")
    val results : List<MovieDto>? = emptyList(),
    @SerialName("total_pages")
    val totalPages : Int? = null,
    @SerialName("total_results")
    val totalResults: Int? = null
)
