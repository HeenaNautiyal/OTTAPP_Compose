package com.shiki.ott_ai.di

import com.shiki.ott_ai.data.remote.FirebaseMessagingDataSource
import com.shiki.ott_ai.data.repository.NotificationRepositoryImpl
import com.shiki.ott_ai.domain.repository.NotificationRepository
import com.shiki.ott_ai.domain.usecase.GetFcmTokenUseCase
import com.shiki.ott_ai.domain.usecase.ObserveFcmTokenUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NotificationModule {

    @Binds
    @Singleton
    abstract fun bindNotificationRepository
                (impl: NotificationRepositoryImpl
    ): NotificationRepository

}
@Module
@InstallIn(SingletonComponent::class)
object NotificationProvideModule{
    @Provides
    @Singleton
    fun provideFirebaseMessagingDataSource()
    : FirebaseMessagingDataSource {
        return FirebaseMessagingDataSource()
    }
    @Provides

    fun provideGetFcmTokenUseCase(
        repository: NotificationRepository
    ): GetFcmTokenUseCase {
        return GetFcmTokenUseCase(repository)
    }

    @Provides
    fun provideObserveFcmTokenUseCase(
        repository: NotificationRepository
    ): ObserveFcmTokenUseCase {
        return ObserveFcmTokenUseCase(repository)
    }
}