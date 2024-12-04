package com.jhtest.storibanktest.domain.repository

import com.jhtest.storibanktest.data.models.UserData
import kotlinx.coroutines.flow.Flow

interface UserCloudStorageRepository {

    suspend fun saveUserData(
        userId: String,
        name: String,
        lastName: String,
        email: String,
        faceId: String
    )

    suspend fun getUserData(userId: String): Flow<Result<UserData>>
}