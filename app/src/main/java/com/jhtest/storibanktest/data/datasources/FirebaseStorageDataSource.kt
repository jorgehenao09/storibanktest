package com.jhtest.storibanktest.data.datasources

import com.jhtest.storibanktest.data.models.BankTransaction
import com.jhtest.storibanktest.data.models.BankTransactionBody
import com.jhtest.storibanktest.data.models.UserData
import com.jhtest.storibanktest.ui.viewmodels.SignUpUserInfo

interface FirebaseStorageDataSource {

    suspend fun saveUserData(signUpUserInfo: SignUpUserInfo)

    suspend fun getUserData(userId: String): UserData

    suspend fun getUserTransactions(userId: String): BankTransactionBody
}