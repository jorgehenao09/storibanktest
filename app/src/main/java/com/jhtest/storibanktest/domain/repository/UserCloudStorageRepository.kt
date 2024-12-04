package com.jhtest.storibanktest.domain.repository

import com.jhtest.storibanktest.data.models.UserData
import com.jhtest.storibanktest.ui.viewmodels.SignUpUserInfo
import kotlinx.coroutines.flow.Flow

interface UserCloudStorageRepository {

    suspend fun saveUserData(signUpUserInfo: SignUpUserInfo)

    fun getUserData(userId: String): Flow<Result<UserData>>
}