package com.shiki.ott_ai.domain.model

data class NotificationUIState(
    val token: String? = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
