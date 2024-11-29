package com.jhtest.storibanktest.di

import com.jhtest.storibanktest.data.repository.SplashRepositoryImpl
import com.jhtest.storibanktest.data.repository.datasources.UserPreferencesProtoDataStore
import com.jhtest.storibanktest.domain.repository.SplashRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class AuthenticationModule {

    @Provides
    @ViewModelScoped
    fun provideSplashRepository(
        userPreferencesProtoDataStore: UserPreferencesProtoDataStore
    ): SplashRepository = SplashRepositoryImpl(
        userPreferencesProtoDataStore = userPreferencesProtoDataStore
    )
}