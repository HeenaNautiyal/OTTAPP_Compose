package com.shiki.ott_ai.data.mapper

import com.shiki.ott_ai.data.remote.dto.MovieClipDto
import com.shiki.ott_ai.data.remote.dto.VideoDto
import com.shiki.ott_ai.domain.model.MovieClip
import com.shiki.ott_ai.data.remote.dto.Video

fun VideoDto.toDomain(): Video {
    return Video(
        id = id ?: "",
        key = key ?: "",
        name = name ?: "",
        official = official ?: false,
        publishedAt = published_at ?: "",
        site = site ?: "",
        type = type ?: "",
        size = size ?: 0
    )
}

fun MovieClipDto.toDomain(): MovieClip {
    return MovieClip(
        results = results?.map { it.toDomain() },
        id = id ?: 0
    )
}
