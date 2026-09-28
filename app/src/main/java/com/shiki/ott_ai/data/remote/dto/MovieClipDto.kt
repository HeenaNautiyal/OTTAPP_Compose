package com.shiki.ott_ai.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieClipDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("results")
    val results: List<VideoDto>? = null
)
