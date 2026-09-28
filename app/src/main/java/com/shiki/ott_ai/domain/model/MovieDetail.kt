package com.shiki.ott_ai.domain.model

import com.shiki.ott_ai.data.remote.dto.Credits
import com.shiki.ott_ai.data.remote.dto.Genre
import com.shiki.ott_ai.data.remote.dto.Video

data class MovieDetail (
    val backdrop_path: String?,
    val credits: Credits?,
    val genres: List<Genre>,
    val id: Int?,
    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val runtime: Int?,
    val title: String?,
    val videos: List<Video>,
    val vote_average: Double?
)