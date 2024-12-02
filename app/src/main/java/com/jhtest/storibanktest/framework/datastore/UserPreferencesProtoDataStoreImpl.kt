package com.jhtest.storibanktest.framework.datastore

import androidx.datastore.core.DataStore
import com.jhtest.storibanktest.data.datastore.UserPreferencesProto
import com.jhtest.storibanktest.data.datasources.UserPreferencesProtoDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserPreferencesProtoDataStoreImpl @Inject constructor(
    private val userDataStore: DataStore<UserPreferencesProto>
): UserPreferencesProtoDataStore {

    override val userDataProto: Flow<UserPreferencesProto>
        get() = userDataStore.data

    override suspend fun saveUserData(userPreferences: UserPreferencesProto) {
        userDataStore.updateData { userData ->
            userData.toBuilder().mergeFrom(userPreferences).build()
        }
    }

    override suspend fun clear() {
        userDataStore.updateData { it.toBuilder().clear().build() }
    }
}

const val USER_PREFERENCES = "user_preferences"