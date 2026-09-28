package com.shiki.ott_ai.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultDto(
    @SerialName("id")
    val id: String,
    val key: String,
    val name: String,
    val site: String,
    val type: String
)