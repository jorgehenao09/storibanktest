package com.jhtest.storibanktest.data.datasources

import com.jhtest.storibanktest.data.models.UserData

interface FirebaseFirestoreDataSource {

    suspend fun saveUserData(
        userId: String,
        name: String,
        lastName: String,
        email: String,
        faceId: String
    )

    suspend fun getUserData(userId: String): UserData
}