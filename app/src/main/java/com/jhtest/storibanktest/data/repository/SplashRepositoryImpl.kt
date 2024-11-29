package com.jhtest.storibanktest.data.repository

import com.jhtest.storibanktest.data.repository.datasources.UserPreferencesProtoDataStore
import com.jhtest.storibanktest.domain.repository.SplashRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class SplashRepositoryImpl(
    private val userPreferencesProtoDataStore: UserPreferencesProtoDataStore
) : SplashRepository {

    override fun isUserLogged() = flow {
        val isUserLogged = userPreferencesProtoDataStore.userDataProto.first().userLogged
        emit(Result.success(isUserLogged))
    }.catch {
        emit(Result.failure(it))
    }
}