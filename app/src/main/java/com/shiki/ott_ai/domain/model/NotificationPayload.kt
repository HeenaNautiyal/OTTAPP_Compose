package com.shiki.ott_ai.domain.model

data class NotificationPayload (
    val title: String?,
    val body: String?,
    val data: Map<String, String> = emptyMap()
)

@JvmInline
value class FcmToken(val value: String)