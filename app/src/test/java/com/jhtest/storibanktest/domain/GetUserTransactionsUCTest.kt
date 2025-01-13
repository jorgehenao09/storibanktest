package com.jhtest.storibanktest.domain

import com.jhtest.storibanktest.data.mapper.BankTransactionMapper
import com.jhtest.storibanktest.data.models.BankTransactionBody
import com.jhtest.storibanktest.domain.models.BankTransactionListModel
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
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class GetUserTransactionsUCTest {

    private val userStorageRepository: UserStorageRepository = mockk()
    private val userCloudStorageRepository: UserCloudStorageRepository = mockk()
    private val bankTransactionMapper: BankTransactionMapper = mockk()
    private val getUserTransactionsUC =
        GetUserTransactionsUC(
            userStorageRepository,
            userCloudStorageRepository,
            bankTransactionMapper
        )

    @Test
    fun `invoke should return mapped transaction list when all dependencies succeed`() = runTest {
        val userId = "testUserId"
        val transactionResponse = mockk<BankTransactionBody>()
        val mappedTransactionList = mockk<BankTransactionListModel>()

        every { userStorageRepository.getUserId() } returns flowOf(Result.success(userId))
        every { userCloudStorageRepository.getUserTransactions(userId) } returns flowOf(
            Result.success(
                transactionResponse
            )
        )
        every { bankTransactionMapper.mapToBankTransactionListModel(transactionResponse) } returns mappedTransactionList

        val result = getUserTransactionsUC().first()

        assertTrue(result.isSuccess)
        assertEquals(mappedTransactionList, result.getOrNull())

        verify {
            userStorageRepository.getUserId()
            userCloudStorageRepository.getUserTransactions(userId)
            bankTransactionMapper.mapToBankTransactionListModel(transactionResponse)
        }
    }

    @Test
    fun `invoke should return failure when userStorageRepository fails`() = runTest {
        val exception = Exception("Failed to get user ID")
        every { userStorageRepository.getUserId() } returns flowOf(Result.failure(exception))

        val result = getUserTransactionsUC().first()

        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(exception, result.exceptionOrNull())

        verify {
            userStorageRepository.getUserId()
        }
    }

    @Test
    fun `invoke should return failure when userCloudStorageRepository fails`() = runTest {
        val userId = "testUserId"
        val exception = Exception("Failed to get user transactions")

        every { userStorageRepository.getUserId() } returns flowOf(Result.success(userId))
        every { userCloudStorageRepository.getUserTransactions(userId) } returns flowOf(
            Result.failure(
                exception
            )
        )

        val result = getUserTransactionsUC().first()

        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(exception, result.exceptionOrNull())

        verify {
            userStorageRepository.getUserId()
            userCloudStorageRepository.getUserTransactions(userId)
        }
    }

    @After
    fun tearDown() {
        confirmVerified(userStorageRepository, userCloudStorageRepository, bankTransactionMapper)
    }
}
