package com.shiki.ott_ai.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresResponseDto(
    @SerialName("genres")
    val genres: List<GenresDto>
)

@Serializable
data class GenresDto (
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("name")
    val name: String? = null
)
