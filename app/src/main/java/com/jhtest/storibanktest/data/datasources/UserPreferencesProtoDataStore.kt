package com.jhtest.storibanktest.data.datasources

import com.jhtest.storibanktest.data.datastore.UserPreferencesProto
import kotlinx.coroutines.flow.Flow

interface UserPreferencesProtoDataStore {

    val userDataProto: Flow<UserPreferencesProto>

    suspend fun saveUserData(userPreferences: UserPreferencesProto)

    suspend fun clear()
}