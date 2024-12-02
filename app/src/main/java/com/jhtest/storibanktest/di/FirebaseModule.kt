package com.jhtest.storibanktest.di

import com.google.firebase.auth.FirebaseAuth
import com.jhtest.storibanktest.data.datasources.FirebaseAuthDataSource
import com.jhtest.storibanktest.framework.firebase.FirebaseAuthDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseAuthDataSource(
        firebaseAuth: FirebaseAuth
    ): FirebaseAuthDataSource = FirebaseAuthDataSourceImpl(firebaseAuth)
}