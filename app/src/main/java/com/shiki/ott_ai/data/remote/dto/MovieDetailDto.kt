package com.shiki.ott_ai.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailDto(
    @SerialName("backdrop_path")
    val backdrop_path: String? = null,
    @SerialName("credits")
    val credits: Credits? = null,
    @SerialName("genres")
    val genres: List<GenresDto>? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("poster_path")
    val poster_path: String? = null,
    @SerialName("release_date")
    val release_date: String? = null,
    @SerialName("runtime")
    val runtime: Int? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("videos")
    val videos: MovieClipDto? = null,
    @SerialName("vote_average")
    val vote_average: Double? = null
)
