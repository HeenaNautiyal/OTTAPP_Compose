package com.shiki.ott_ai.domain.model

import com.shiki.ott_ai.data.remote.dto.Video

data class MovieClip(
    val id: Int,
    val results: List<Video>?
)
