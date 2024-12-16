package com.jhtest.storibanktest.data.repository

import com.jhtest.storibanktest.data.datasources.UserPreferencesProtoDataStore
import com.jhtest.storibanktest.data.datastore.UserPreferencesProto
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class UserStorageRepositoryImplTest {
    private lateinit var userStorageRepository: UserStorageRepositoryImpl
    private val userPreferencesProtoDataStore: UserPreferencesProtoDataStore = mockk()

    @Before
    fun setUp() {
        userStorageRepository = UserStorageRepositoryImpl(userPreferencesProtoDataStore)
    }

    @Test
    fun `isUserLogged should emit success when user is logged`() =
        runTest {
            val userLogged = true
            val userPreferencesProto =
                mockk<UserPreferencesProto>(relaxed = true) {
                    every { this@mockk.userLogged } returns userLogged
                }

            every { userPreferencesProtoDataStore.userDataProto } returns
                flow { emit(userPreferencesProto) }

            val result = userStorageRepository.isUserLogged().toList()

            assertEquals(1, result.size)
            assertEquals(Result.success(userLogged), result.first())
            verify { userPreferencesProtoDataStore.userDataProto }
        }

    @Test
    fun `isUserLogged should emit failure when an exception is thrown`() =
        runTest {
            val error = Exception("Failed to fetch user data")

            every { userPreferencesProtoDataStore.userDataProto } returns flow { throw error }

            val result = userStorageRepository.isUserLogged().toList()

            assertEquals(1, result.size)
            assertEquals(Result.failure<Boolean>(error), result.first())
            verify { userPreferencesProtoDataStore.userDataProto }
        }

    @Test
    fun `saveUserData should save user data successfully`() =
        runTest {
            val userId = "12345"
            val name = "John"
            val lastName = "Doe"
            val email = "test@example.com"

            coJustRun { userPreferencesProtoDataStore.saveUserData(any()) }

            userStorageRepository.saveUserData(userId, name, lastName, email)

            coVerify {
                userPreferencesProtoDataStore.saveUserData(
                    match {
                        it.userLogged &&
                            it.customerId == userId &&
                            it.firstName == name &&
                            it.lastName == lastName &&
                            it.email == email
                    },
                )
            }
        }
}
