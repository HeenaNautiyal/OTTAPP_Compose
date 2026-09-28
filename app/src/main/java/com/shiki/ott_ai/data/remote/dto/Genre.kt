package com.shiki.ott_ai.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: Int,
    val name: String
)