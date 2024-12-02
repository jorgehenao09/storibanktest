package com.jhtest.storibanktest.data.datasources

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthDataSource {

    suspend fun loginUser(email: String, password: String): FirebaseUser
}