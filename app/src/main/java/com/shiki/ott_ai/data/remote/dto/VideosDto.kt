package com.shiki.ott_ai.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideosDto(
    @SerialName("results")
    val results: List<VideoDto>? = null
)
