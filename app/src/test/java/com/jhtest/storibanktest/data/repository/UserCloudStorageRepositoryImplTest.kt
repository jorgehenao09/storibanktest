package com.jhtest.storibanktest.data.repository

import com.jhtest.storibanktest.data.datasources.FirebaseStorageDataSource
import com.jhtest.storibanktest.data.models.UserData
import com.jhtest.storibanktest.ui.viewmodels.SignUpUserInfo
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class UserCloudStorageRepositoryImplTest {

    private lateinit var userCloudStorageRepository: UserCloudStorageRepositoryImpl
    private val firebaseFirestoreDataSource: FirebaseStorageDataSource = mockk()

    @Before
    fun setUp() {
        userCloudStorageRepository = UserCloudStorageRepositoryImpl(firebaseFirestoreDataSource)
    }

    @Test
    fun `saveUserData should call firebaseFirestoreDataSource with correct parameters`() = runTest {
        val signUpUserInfo = SignUpUserInfo(
            userId = "12345",
            name = "John",
            lastName = "Doe",
            email = "test@example.com"
        )

        coJustRun { firebaseFirestoreDataSource.saveUserData(signUpUserInfo) }

        userCloudStorageRepository.saveUserData(signUpUserInfo)

        coVerify { firebaseFirestoreDataSource.saveUserData(signUpUserInfo) }
    }

    @Test
    fun `getUserData should emit success when firebaseFirestoreDataSource returns user data`() = runTest {
        val userId = "12345"
        val userData = UserData(
            name = "John",
            lastName = "Doe",
            email = "test@example.com"
        )

        coEvery { firebaseFirestoreDataSource.getUserData(userId) } returns userData

        val result = userCloudStorageRepository.getUserData(userId).toList()

        assertEquals(1, result.size)
        assertEquals(Result.success(userData), result.first())
        coVerify { firebaseFirestoreDataSource.getUserData(userId) }
    }

    @Test
    fun `getUserData should emit failure when firebaseFirestoreDataSource throws an exception`() = runTest {
        val userId = "12345"
        val error = Exception("Failed to fetch user data")

        coEvery { firebaseFirestoreDataSource.getUserData(userId) } throws error

        val result = userCloudStorageRepository.getUserData(userId).toList()

        assertEquals(1, result.size)
        assertEquals(Result.failure<UserData>(error), result.first())
        coVerify { firebaseFirestoreDataSource.getUserData(userId) }
    }
}