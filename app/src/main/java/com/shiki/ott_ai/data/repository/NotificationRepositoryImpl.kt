package com.shiki.ott_ai.data.repository

import android.preference.Preference
import com.shiki.ott_ai.data.remote.FirebaseMessagingDataSource
import com.shiki.ott_ai.domain.model.FcmToken
import com.shiki.ott_ai.domain.model.NotificationPayload
import com.shiki.ott_ai.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val dataSource: FirebaseMessagingDataSource
): NotificationRepository {
    private val _tokenFlow= MutableSharedFlow<FcmToken>(replay = 1)
    private val _notificationFlow = MutableSharedFlow<NotificationPayload>()

    override suspend fun getToken(): Result<FcmToken> {
        return dataSource.getToken().map { FcmToken(it) }
            .onSuccess { saveToken(it) }
    }

    override fun observeToken(): Flow<FcmToken> = _tokenFlow.asSharedFlow()

    override suspend fun saveToken(token: FcmToken) {
        _tokenFlow.emit(token)
    }

    override suspend fun onNewNotification(payload: NotificationPayload) {
        TODO("Not yet implemented")
    }


}