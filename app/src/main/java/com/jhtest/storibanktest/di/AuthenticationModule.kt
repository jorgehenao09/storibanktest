package com.jhtest.storibanktest.di

import com.jhtest.storibanktest.data.datasources.FirebaseAuthDataSource
import com.jhtest.storibanktest.data.datasources.FirebaseStorageDataSource
import com.jhtest.storibanktest.data.repository.UserStorageRepositoryImpl
import com.jhtest.storibanktest.data.datasources.UserPreferencesProtoDataStore
import com.jhtest.storibanktest.data.repository.SignInRepositoryImpl
import com.jhtest.storibanktest.data.repository.SignUpRepositoryImpl
import com.jhtest.storibanktest.data.repository.UserCloudStorageRepositoryImpl
import com.jhtest.storibanktest.domain.mapper.UserDataMapper
import com.jhtest.storibanktest.domain.repository.SignInRepository
import com.jhtest.storibanktest.domain.repository.SignUpRepository
import com.jhtest.storibanktest.domain.repository.UserCloudStorageRepository
import com.jhtest.storibanktest.domain.repository.UserStorageRepository
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
    ): UserStorageRepository = UserStorageRepositoryImpl(
        userPreferencesProtoDataStore = userPreferencesProtoDataStore
    )

    @Provides
    @ViewModelScoped
    fun provideLoginRepository(
        firebaseAuthDataSource: FirebaseAuthDataSource
    ): SignInRepository = SignInRepositoryImpl(firebaseAuthDataSource)

    @Provides
    @ViewModelScoped
    fun provideSignUpRepository(
        firebaseAuthDataSource: FirebaseAuthDataSource
    ): SignUpRepository = SignUpRepositoryImpl(firebaseAuthDataSource)

    @Provides
    @ViewModelScoped
    fun provideUserCloudStorageRepository(
        firebaseFirestoreDataSource: FirebaseStorageDataSource
    ): UserCloudStorageRepository = UserCloudStorageRepositoryImpl(firebaseFirestoreDataSource)

    @Provides
    @ViewModelScoped
    fun provideUserDataMapper() = UserDataMapper()
}