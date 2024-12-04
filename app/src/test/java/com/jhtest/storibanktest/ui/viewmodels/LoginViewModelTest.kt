package com.jhtest.storibanktest.ui.viewmodels

import com.jhtest.storibanktest.domain.SignInUserUC
import com.jhtest.storibanktest.ui.authentication.login.states.LoginUiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    private val signInUserUC: SignInUserUC = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = LoginViewModel(signInUserUC, Dispatchers.Main)
    }

    @Test
    fun `onSignInUser should update loginState with success when signInUserUC succeeds`() = runTest {
        val email = "test@example.com"
        val password = "password123"
        val successResult = Result.success(Unit)

        coEvery { signInUserUC.invoke(email, password) } returns flow { emit(successResult) }

        viewModel.setEmail(email to true)
        viewModel.setPassword(password to true)

        val states = mutableListOf<LoginUiState>()
        val job = launch {
            viewModel.loginState.toList(states)
        }

        viewModel.onSignInUser()

        assertEquals(3, states.size)
        assertEquals(LoginUiState(isLoading = true), states[0])
        assertEquals(LoginUiState(isLoading = false, isSuccess = true), states[1])

        job.cancel()
    }

    @Test
    fun `onSignInUser should update loginState with error when signInUserUC fails`() = runTest {
        val email = "test@example.com"
        val password = "password123"
        val errorMessage = "Login failed"
        val failureResult = Result.failure<Unit>(Exception(errorMessage))

        coEvery { signInUserUC.invoke(email, password) } returns flow { emit(failureResult) }

        viewModel.setEmail(email to true)
        viewModel.setPassword(password to true)

        val states = mutableListOf<LoginUiState>()
        val job = launch {
            viewModel.loginState.toList(states)
        }

        viewModel.onSignInUser()

        assertEquals(3, states.size)
        assertEquals(LoginUiState(isLoading = true), states[0])
        assertEquals(
            LoginUiState(isLoading = false, messageError = errorMessage),
            states[1]
        )

        job.cancel()
    }

    @Test
    fun `setEmail should update loginUserInfo and emailState`() {
        // Datos simulados
        val email = "test@example.com"
        val isValid = true

        // Ejecución
        viewModel.setEmail(email to isValid)

        // Verificaciones
        assertEquals(email, viewModel.getEmail())
        assertTrue(viewModel.isButtonEnabled.not()) // Password aún no está configurado
    }

    @Test
    fun `setPassword should update loginUserInfo and passwordState`() {
        // Datos simulados
        val password = "password123"
        val isValid = true

        // Ejecución
        viewModel.setPassword(password to isValid)

        // Verificaciones
        assertEquals(password, viewModel.getPassword())
        assertFalse(viewModel.isButtonEnabled) // Email aún no está configurado
    }

    @Test
    fun `isButtonEnabled should be true when email and password are valid`() {
        // Configuración
        viewModel.setEmail("test@example.com" to true)
        viewModel.setPassword("password123" to true)

        // Verificación
        assertTrue(viewModel.isButtonEnabled)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}