package com.shiki.ott_ai.data.mapper

import com.shiki.ott_ai.data.remote.dto.MovieDetailDto
import com.shiki.ott_ai.domain.model.MovieDetail

fun MovieDetailDto.toDomain(): MovieDetail {
    return MovieDetail(
        id = id ?: 0,
        title = title ?: "",
        overview = overview ?: "",
        backdrop_path = backdrop_path ?: "",
        credits = credits,
        genres = genres?.map { it.toDomain() } ?: emptyList(),
        poster_path = poster_path ?: "",
        release_date = release_date ?: "",
        runtime = runtime ?: 0,
        videos = videos?.results?.map { it.toDomain() } ?: emptyList(),
        vote_average = vote_average ?: 0.0
    )
}
