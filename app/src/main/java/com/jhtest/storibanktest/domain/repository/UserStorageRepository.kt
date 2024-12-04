package com.jhtest.storibanktest.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserStorageRepository {

    fun isUserLogged(): Flow<Result<Boolean>>

    suspend fun saveUserData(userId: String, name: String, lastName: String, email: String)
}