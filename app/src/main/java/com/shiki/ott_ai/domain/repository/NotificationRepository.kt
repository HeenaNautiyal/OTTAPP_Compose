package com.shiki.ott_ai.domain.repository

import com.shiki.ott_ai.domain.model.FcmToken
import com.shiki.ott_ai.domain.model.NotificationPayload
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun getToken(): Result<FcmToken>
    fun observeToken(): Flow<FcmToken>
    suspend fun saveToken(token: FcmToken)
    suspend fun onNewNotification(payload: NotificationPayload)
}