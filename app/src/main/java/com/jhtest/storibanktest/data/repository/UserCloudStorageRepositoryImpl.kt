package com.jhtest.storibanktest.data.repository

import com.jhtest.storibanktest.data.datasources.FirebaseFirestoreDataSource
import com.jhtest.storibanktest.data.models.UserData
import com.jhtest.storibanktest.domain.repository.UserCloudStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserCloudStorageRepositoryImpl @Inject constructor(
    private val firebaseFirestoreDataSource: FirebaseFirestoreDataSource
) : UserCloudStorageRepository {

    override suspend fun saveUserData(
        userId: String,
        name: String,
        lastName: String,
        email: String,
        faceId: String
    ) {
        firebaseFirestoreDataSource.saveUserData(userId, name, lastName, email, faceId)
    }

    override suspend fun getUserData(userId: String): Flow<Result<UserData>> = flow {
        val userData = firebaseFirestoreDataSource.getUserData(userId)
        emit(Result.success(userData))
    }.catch {
        emit(Result.failure(it))
    }
}