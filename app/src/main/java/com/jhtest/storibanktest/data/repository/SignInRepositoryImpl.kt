package com.jhtest.storibanktest.data.repository

import com.google.firebase.auth.FirebaseUser
import com.jhtest.storibanktest.data.datasources.FirebaseAuthDataSource
import com.jhtest.storibanktest.domain.repository.SignInRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val firebaseAuthDataSource: FirebaseAuthDataSource
) : SignInRepository {

    override suspend fun signInUser(email: String, password: String): Flow<Result<FirebaseUser>> =
        flow {
            val userData = firebaseAuthDataSource.signInUser(email, password)
            emit(Result.success(userData))
        }.catch {
            emit(Result.failure(it))
        }
}