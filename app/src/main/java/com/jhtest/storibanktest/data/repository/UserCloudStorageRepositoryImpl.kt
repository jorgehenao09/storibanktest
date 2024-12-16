package com.jhtest.storibanktest.data.repository

import com.jhtest.storibanktest.data.datasources.FirebaseStorageDataSource
import com.jhtest.storibanktest.data.models.UserData
import com.jhtest.storibanktest.domain.repository.UserCloudStorageRepository
import com.jhtest.storibanktest.ui.viewmodels.SignUpUserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserCloudStorageRepositoryImpl
    @Inject
    constructor(
        private val firebaseFirestoreDataSource: FirebaseStorageDataSource,
    ) : UserCloudStorageRepository {
        override suspend fun saveUserData(signUpUserInfo: SignUpUserInfo) {
            firebaseFirestoreDataSource.saveUserData(signUpUserInfo)
        }

        override fun getUserData(userId: String): Flow<Result<UserData>> =
            flow {
                val userData = firebaseFirestoreDataSource.getUserData(userId)
                emit(Result.success(userData))
            }.catch {
                emit(Result.failure(it))
            }

        override fun getUserTransactions(userId: String) =
            flow {
                val bankTransaction = firebaseFirestoreDataSource.getUserTransactions(userId)
                emit(Result.success(bankTransaction))
            }.catch {
                emit(Result.failure(it))
            }
    }
