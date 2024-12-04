package com.jhtest.storibanktest.data.datasources

import com.google.firebase.auth.FirebaseUser

interface FirebaseAuthDataSource {

    suspend fun signInUser(email: String, password: String): FirebaseUser

    suspend fun signUpUser(
        email: String,
        password: String): FirebaseUser
}