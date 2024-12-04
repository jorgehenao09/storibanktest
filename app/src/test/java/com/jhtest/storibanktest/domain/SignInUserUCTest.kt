package com.jhtest.storibanktest.domain

import com.google.firebase.auth.FirebaseUser
import com.jhtest.storibanktest.data.models.UserData
import com.jhtest.storibanktest.domain.repository.SignInRepository
import com.jhtest.storibanktest.domain.repository.UserCloudStorageRepository
import com.jhtest.storibanktest.domain.repository.UserStorageRepository
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class SignInUserUCTest {

    private lateinit var signInUserUC: SignInUserUC
    private val signInRepository: SignInRepository = mockk()
    private val userStorageRepository: UserStorageRepository = mockk()
    private val userCloudStorageRepository: UserCloudStorageRepository = mockk()

    @Before
    fun setUp() {
        signInUserUC =
            SignInUserUC(signInRepository, userStorageRepository, userCloudStorageRepository)
    }

    @Test
    fun `invoke should emit success when all operations succeed`() = runTest {
        val email = "test@example.com"
        val password = "password123"
        val uid = "12345"
        val userResponse = mockk<FirebaseUser>()
        val userData = UserData(name = "John", lastName = "Doe", email = "test@example.com")

        every { userResponse.uid } returns uid

        coEvery { signInRepository.signInUser(email, password) } returns flow {
            emit(Result.success(userResponse))
        }
        coEvery { userCloudStorageRepository.getUserData(uid) } returns flow {
            emit(Result.success(userData))
        }
        coJustRun {
            userStorageRepository.saveUserData(
                uid,
                userData.name,
                userData.lastName,
                userData.email
            )
        }

        val result = signInUserUC(email, password).toList()

        assertEquals(Result.success(Unit), result.first())
        coVerifyOrder {
            signInRepository.signInUser(email, password)
            userCloudStorageRepository.getUserData(uid)
            userStorageRepository.saveUserData(
                uid,
                userData.name,
                userData.lastName,
                userData.email
            )
        }
    }

    @Test
    fun `invoke should emit failure when signInRepository fails`() = runTest {
        val email = "test@example.com"
        val password = "password123"
        val error = Exception("Sign in failed")

        coEvery { signInRepository.signInUser(email, password) } returns flow {
            emit(
                Result.failure(
                    error
                )
            )
        }

        val result = signInUserUC(email, password).toList()

        assertEquals(Result.failure<Unit>(error), result.first())
        coVerify { signInRepository.signInUser(email, password) }
        coVerify(exactly = 0) { userCloudStorageRepository.getUserData(any()) }
        coVerify(exactly = 0) { userStorageRepository.saveUserData(any(), any(), any(), any()) }
    }

    @Test
    fun `invoke should emit failure when userCloudStorageRepository fails`() = runTest {
        val email = "test@example.com"
        val password = "password123"
        val uid = "12345"
        val userResponse = mockk<FirebaseUser>()
        val error = Exception("Failed to fetch user data")

        every { userResponse.uid } returns uid

        coEvery { signInRepository.signInUser(email, password) } returns flow {
            emit(Result.success(userResponse))
        }
        coEvery { userCloudStorageRepository.getUserData(uid) } returns flow {
            emit(Result.failure(error))
        }

        val result = signInUserUC(email, password).toList()

        assertEquals(Result.failure<Unit>(error), result.first())
        coVerifyOrder {
            signInRepository.signInUser(email, password)
            userCloudStorageRepository.getUserData(uid)
        }
        coVerify(exactly = 0) { userStorageRepository.saveUserData(any(), any(), any(), any()) }
    }
}