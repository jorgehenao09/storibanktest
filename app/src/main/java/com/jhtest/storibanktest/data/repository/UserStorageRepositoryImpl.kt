package com.jhtest.storibanktest.data.repository

import com.jhtest.storibanktest.data.datasources.UserPreferencesProtoDataStore
import com.jhtest.storibanktest.data.datastore.UserPreferencesProto
import com.jhtest.storibanktest.domain.repository.UserStorageRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserStorageRepositoryImpl
    @Inject
    constructor(
        private val userPreferencesProtoDataStore: UserPreferencesProtoDataStore,
    ) : UserStorageRepository {
        override fun isUserLogged() =
            flow {
                val isUserLogged = userPreferencesProtoDataStore.userDataProto.first().userLogged
                emit(Result.success(isUserLogged))
            }.catch {
                emit(Result.failure(it))
            }

        override suspend fun saveUserData(
            userId: String,
            name: String,
            lastName: String,
            email: String,
        ) {
            userPreferencesProtoDataStore.saveUserData(
                UserPreferencesProto.newBuilder()
                    .setUserLogged(true)
                    .setCustomerId(userId)
                    .setLastName(lastName)
                    .setEmail(email)
                    .setFirstName(name)
                    .build(),
            )
        }

        override fun getUserId() =
            flow {
                val userId = userPreferencesProtoDataStore.userDataProto.first().customerId
                emit(Result.success(userId))
            }.catch {
                emit(Result.failure(it))
            }
    }
