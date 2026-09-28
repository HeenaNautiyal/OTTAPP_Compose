package com.shiki.ott_ai.data.remote.dto

import com.shiki.ott_ai.domain.model.Cast
import kotlinx.serialization.Serializable

@Serializable
data class Credits(
    val cast: List<Cast>
)