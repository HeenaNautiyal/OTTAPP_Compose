package com.shiki.ott_ai.di

import com.shiki.ott_ai.data.repository.MovieRepositoryImpl
import com.shiki.ott_ai.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindrepository(impl : MovieRepositoryImpl): MovieRepository
}