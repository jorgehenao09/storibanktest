package com.jhtest.storibanktest.data.repository

import com.google.firebase.auth.FirebaseUser
import com.jhtest.storibanktest.data.datasources.FirebaseAuthDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class SignInRepositoryImplTest {

    private lateinit var signInRepository: SignInRepositoryImpl
    private val firebaseAuthDataSource: FirebaseAuthDataSource = mockk()

    @Before
    fun setUp() {
        signInRepository = SignInRepositoryImpl(firebaseAuthDataSource)
    }

    @Test
    fun `signInUser should emit success when firebaseAuthDataSource returns a user`() = runTest {
        val email = "test@example.com"
        val password = "password123"
        val firebaseUser = mockk<FirebaseUser>()

        coEvery { firebaseAuthDataSource.signInUser(email, password) } returns firebaseUser

        val result = signInRepository.signInUser(email, password).toList()

        assertEquals(1, result.size)
        assertEquals(Result.success(firebaseUser), result.first())
        coVerify { firebaseAuthDataSource.signInUser(email, password) }
    }

    @Test
    fun `signInUser should emit failure when firebaseAuthDataSource throws an exception`() = runTest {
        val email = "test@example.com"
        val password = "password123"
        val error = Exception("Login failed")

        coEvery { firebaseAuthDataSource.signInUser(email, password) } throws error

        val result = signInRepository.signInUser(email, password).toList()

        assertEquals(1, result.size)
        assertEquals(Result.failure<FirebaseUser>(error), result.first())
        coVerify { firebaseAuthDataSource.signInUser(email, password) }
    }
}