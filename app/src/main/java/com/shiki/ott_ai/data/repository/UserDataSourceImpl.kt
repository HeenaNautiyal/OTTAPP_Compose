package com.shiki.ott_ai.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.shiki.ott_ai.data.remote.UserDataSource
import com.shiki.ott_ai.userdb.UserDatabse
import com.shiki.ott_ai.userdb.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserDataSourceImpl(
    db: UserDatabse
): UserDataSource {
    private val queries = db.userEntityQueries

    override suspend fun getUserByName(Username: String): UserEntity? {
        return withContext(Dispatchers.IO){
             queries.getUserByName(Username).executeAsOneOrNull()
        }
    }

    override suspend fun insertUser(firstName: String, lastName: String, password: String) {
        withContext(Dispatchers.IO) {
            queries.insertUser(
                firstName = firstName,
                lastName = lastName,
                password = password
            )
        }
    }

    override fun getAllUser(): Flow<List<UserEntity>> {
        return queries.getAllUsers()
            .asFlow()
            .mapToList(Dispatchers.IO)
    }
}
