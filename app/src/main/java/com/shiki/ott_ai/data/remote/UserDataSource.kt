package com.shiki.ott_ai.data.remote

import com.shiki.ott_ai.userdb.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    suspend fun getUserByName(username: String): UserEntity?
    suspend fun insertUser(firstName: String, lastName: String, password: String)
    fun getAllUser(): Flow<List<UserEntity>>
}