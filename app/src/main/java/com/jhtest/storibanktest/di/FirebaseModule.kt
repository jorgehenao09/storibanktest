package com.jhtest.storibanktest.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.jhtest.storibanktest.data.datasources.FirebaseAuthDataSource
import com.jhtest.storibanktest.data.datasources.FirebaseStorageDataSource
import com.jhtest.storibanktest.framework.firebase.FirebaseAuthDataSourceImpl
import com.jhtest.storibanktest.framework.firebase.FirebaseStorageDataSourceImpl
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
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseAuthDataSource(
        firebaseAuth: FirebaseAuth
    ): FirebaseAuthDataSource = FirebaseAuthDataSourceImpl(firebaseAuth)

    @Provides
    @Singleton
    fun provideFirebaseStorageDataSource(
        firebaseFirestore: FirebaseFirestore,
        firebaseStorage: FirebaseStorage
    ): FirebaseStorageDataSource = FirebaseStorageDataSourceImpl(firebaseFirestore, firebaseStorage)
}