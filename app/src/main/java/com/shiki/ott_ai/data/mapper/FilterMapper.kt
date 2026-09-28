package com.shiki.ott_ai.data.mapper

import com.shiki.ott_ai.data.remote.dto.GenresDto
import com.shiki.ott_ai.data.remote.dto.Genre

fun GenresDto.toDomain(): Genre {
    return Genre(
        id = id ?: 0,
        name = name?: ""
    )
}
