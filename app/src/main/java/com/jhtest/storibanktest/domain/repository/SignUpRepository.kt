package com.jhtest.storibanktest.domain.repository

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface SignUpRepository {
    fun signUpUser(
        email: String,
        password: String,
    ): Flow<Result<FirebaseUser>>
}
