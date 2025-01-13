package com.jhtest.storibanktest.domain

import com.jhtest.storibanktest.domain.repository.UserStorageRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class IsUserLoggedUCTest {

    private val userStorageRepository: UserStorageRepository = mockk()
    private val isUserLoggedUC = IsUserLoggedUC(userStorageRepository)

    @Test
    fun `invoke should return true when user is logged in`() =
        runTest {
            every { userStorageRepository.isUserLogged() } returns flowOf(Result.success(true))

            val result = isUserLoggedUC().first()

            Assert.assertTrue(result.isSuccess)
            assertEquals(true, result.getOrNull())
        }

    @Test
    fun `invoke should return false when user is not logged in`() =
        runTest {
            every { userStorageRepository.isUserLogged() } returns flowOf(Result.success(false))

            val result = isUserLoggedUC().first()

            Assert.assertTrue(result.isSuccess)
            assertEquals(false, result.getOrNull())
        }

    @Test
    fun `invoke should return failure when userStorageRepository fails`() =
        runTest {
            val exception = Exception("Failed to check user login status")
            every { userStorageRepository.isUserLogged() } returns flowOf(Result.failure(exception))

            val result = isUserLoggedUC().first()

            Assert.assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
        }

    @After
    fun tearDown() {
        verify {
            userStorageRepository.isUserLogged()
        }
        confirmVerified(userStorageRepository)
    }
}
