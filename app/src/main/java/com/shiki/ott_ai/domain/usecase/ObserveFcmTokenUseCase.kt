package com.shiki.ott_ai.domain.usecase

import com.shiki.ott_ai.domain.repository.NotificationRepository
import javax.inject.Inject

class ObserveFcmTokenUseCase @Inject constructor(
    private val repository: NotificationRepository
) {
    operator fun invoke() = repository.observeToken()
}
