package com.shiki.ott_ai.data.mapper


import com.shiki.ott_ai.data.remote.dto.MovieDto
import com.shiki.ott_ai.domain.model.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id ?: 0,
        title = title ?: "",
        overview = overview ?: "",
        posterPath = poster_path ?: "",
        voteAverage = vote_average ?: 0.0,
        backdropPath = backdrop_path ?: "",
        releaseDate = release_date ?: ""
    )
}


