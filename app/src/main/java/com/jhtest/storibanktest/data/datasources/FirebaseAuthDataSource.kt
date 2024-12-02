package com.jhtest.storibanktest.data.datasources

import com.google.firebase.auth.FirebaseUser

interface FirebaseAuthDataSource {

    suspend fun loginUser(email: String, password: String): FirebaseUser
}