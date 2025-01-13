package com.jhtest.storibanktest.domain

import com.jhtest.storibanktest.data.models.UserData
import com.jhtest.storibanktest.domain.repository.UserCloudStorageRepository
import com.jhtest.storibanktest.domain.repository.UserStorageRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class GetUserInfoUCTest {

    private val userStorageRepository: UserStorageRepository = mockk()
    private val userCloudStorageRepository: UserCloudStorageRepository = mockk()
    private val getUserInfoUC = GetUserInfoUC(userStorageRepository, userCloudStorageRepository)

    @Test
    fun `invoke should return user name when both repositories succeed`() =
        runTest {
            val userId = "testUserId"
            val userName = "Test User"
            val userData = UserData(name = userName)

            every { userStorageRepository.getUserId() } returns flowOf(Result.success(userId))
            every { userCloudStorageRepository.getUserData(userId) } returns flowOf(
                Result.success(
                    userData
                )
            )

            val result = getUserInfoUC().first()

            assertTrue(result.isSuccess)
            assertEquals(userName, result.getOrNull())

            verify {
                userStorageRepository.getUserId()
                userCloudStorageRepository.getUserData(userId)
            }
        }

    @Test
    fun `invoke should return failure when userStorageRepository fails`() =
        runTest {
            val exception = Exception("Failed to get user ID")
            every { userStorageRepository.getUserId() } returns flowOf(Result.failure(exception))

            val result = getUserInfoUC().first()

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())

            verify {
                userStorageRepository.getUserId()
            }
        }

    @Test
    fun `invoke should return failure when userCloudStorageRepository fails`() =
        runTest {
            val userId = "testUserId"
            val exception = Exception("Failed to get user data")

            every { userStorageRepository.getUserId() } returns flowOf(Result.success(userId))
            every { userCloudStorageRepository.getUserData(userId) } returns flowOf(
                Result.failure(
                    exception
                )
            )

            val result = getUserInfoUC().first()

            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())

            verify {
                userStorageRepository.getUserId()
                userCloudStorageRepository.getUserData(userId)
            }
        }

    @After
    fun tearDown() {
        confirmVerified(userStorageRepository, userCloudStorageRepository)
    }
}
