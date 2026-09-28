package com.shiki.ott_ai.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Cast(
    val character: String?,
    val id: Int?,
    val name: String?,
    val profile_path: String?
)