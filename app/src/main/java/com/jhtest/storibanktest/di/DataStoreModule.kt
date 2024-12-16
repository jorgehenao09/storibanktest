package com.jhtest.storibanktest.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.jhtest.storibanktest.data.datasources.UserPreferencesProtoDataStore
import com.jhtest.storibanktest.data.datastore.UserPreferencesProto
import com.jhtest.storibanktest.framework.datastore.USER_PREFERENCES
import com.jhtest.storibanktest.framework.datastore.UserPreferencesProtoDataStoreImpl
import com.jhtest.storibanktest.framework.datastore.UserPreferencesSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Singleton
    @Provides
    fun dataStoreUserProvide(
        @ApplicationContext appContext: Context,
    ): DataStore<UserPreferencesProto> =
        DataStoreFactory.create(
            produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCES) },
            serializer = UserPreferencesSerializer,
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
        )

    @Provides
    @Singleton
    fun userPreferencesDataStoreProvider(
        userDataStore: DataStore<UserPreferencesProto>
    ): UserPreferencesProtoDataStore = UserPreferencesProtoDataStoreImpl(userDataStore)
}
