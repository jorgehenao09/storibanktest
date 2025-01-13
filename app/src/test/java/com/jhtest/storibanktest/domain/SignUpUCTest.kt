package com.jhtest.storibanktest.domain

import com.google.firebase.auth.FirebaseUser
import com.jhtest.storibanktest.domain.repository.SignUpRepository
import com.jhtest.storibanktest.domain.repository.UserCloudStorageRepository
import com.jhtest.storibanktest.domain.repository.UserStorageRepository
import com.jhtest.storibanktest.ui.viewmodels.SignUpUserInfo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class SignUpUCTest {

    private val signUpRepository: SignUpRepository = mockk()
    private val userStorageRepository: UserStorageRepository = mockk()
    private val userCloudStorageRepository: UserCloudStorageRepository = mockk()

    private val signUpUC =
        SignUpUC(signUpRepository, userStorageRepository, userCloudStorageRepository)

    @Test
    fun `invoke should return success and save user data when sign up succeeds`() = runTest {
        val signUpUserInfo = SignUpUserInfo(
            name = "Test",
            lastName = "User",
            email = "test@example.com",
            password = "password"
        )
        val userResponse = mockk<FirebaseUser>()

        every { userResponse.uid } returns "testUserId"
        coEvery {
            signUpRepository.signUpUser(
                signUpUserInfo.email,
                signUpUserInfo.password
            )
        } returns flowOf(Result.success(userResponse))
        coEvery {
            userStorageRepository.saveUserData(
                "testUserId",
                signUpUserInfo.name,
                signUpUserInfo.lastName,
                signUpUserInfo.email
            )
        } just runs
        coEvery {
            userCloudStorageRepository.saveUserData(
                signUpUserInfo.copy(userId = "testUserId")
            )
        } just runs

        val result = signUpUC(signUpUserInfo).first()

        assertTrue(result.isSuccess)
        assertEquals(Unit, result.getOrNull())

        coVerify {
            signUpRepository.signUpUser(
                signUpUserInfo.email,
                signUpUserInfo.password
            )
            userStorageRepository.saveUserData(
                "testUserId",
                signUpUserInfo.name,
                signUpUserInfo.lastName,
                signUpUserInfo.email
            )
            userCloudStorageRepository.saveUserData(
                signUpUserInfo.copy(userId = "testUserId")
            )
        }
        verify {
            userResponse.uid
        }
    }

    @Test
    fun `invoke should return failure when signUpRepository fails`() = runTest {
        val signUpUserInfo = SignUpUserInfo(
            name = "Test",
            lastName = "User",
            email = "test@example.com",
            password = "password"
        )
        val exception = Exception("Sign up failed")

        coEvery {
            signUpRepository.signUpUser(
                signUpUserInfo.email,
                signUpUserInfo.password
            )
        } returns flowOf(Result.failure(exception))

        val result = signUpUC(signUpUserInfo).first()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())

        coVerify {
            signUpRepository.signUpUser(
                signUpUserInfo.email,
                signUpUserInfo.password
            )
        }
    }

    @After
    fun tearDown() {
        confirmVerified(signUpRepository, userStorageRepository, userCloudStorageRepository)
    }
}
