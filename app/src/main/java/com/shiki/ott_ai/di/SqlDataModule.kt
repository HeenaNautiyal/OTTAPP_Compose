package com.shiki.ott_ai.di

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.shiki.ott_ai.data.remote.UserDataSource
import com.shiki.ott_ai.data.repository.UserDataSourceImpl
import com.shiki.ott_ai.userdb.UserDatabse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SqlDataModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): SqlDriver {
        return AndroidSqliteDriver(
            schema = UserDatabse.Schema,
            context = app,
            name = "user.db"
        )
    }

    @Provides
    @Singleton
    fun provideUserDatabase(driver: SqlDriver): UserDataSource {
        return UserDataSourceImpl(UserDatabse(driver))
    }
}