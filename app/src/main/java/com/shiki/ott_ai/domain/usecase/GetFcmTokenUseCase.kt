package com.shiki.ott_ai.domain.usecase

import com.shiki.ott_ai.domain.repository.NotificationRepository
import javax.inject.Inject

class GetFcmTokenUseCase @Inject constructor(
    private val repository: NotificationRepository) {
    suspend operator fun invoke() = repository.getToken()
}

