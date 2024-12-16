package com.jhtest.storibanktest.ui.viewmodels

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhtest.storibanktest.di.IoDispatcher
import com.jhtest.storibanktest.domain.SignInUserUC
import com.jhtest.storibanktest.ui.authentication.login.states.LoginUiState
import com.jhtest.storibanktest.utils.Empty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

data class LoginUserInfo(
    val email: String = String.Empty,
    val password: String = String.Empty,
)

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val signInUserUC: SignInUserUC,
        @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    ) : ViewModel() {
        private val _loginState: MutableSharedFlow<LoginUiState> =
            MutableSharedFlow()
        val loginState: SharedFlow<LoginUiState>
            get() = _loginState.asSharedFlow()

        private var emailState by mutableStateOf(false)
        private var passwordState by mutableStateOf(false)

        val isButtonEnabled by derivedStateOf {
            emailState && passwordState
        }

        private var loginUserInfo = LoginUserInfo()

        fun onSignInUser() {
            signInUserUC.invoke(loginUserInfo.email, loginUserInfo.password).map {
                it.fold(
                    onSuccess = { _ ->
                        _loginState.emit(
                            LoginUiState(
                                isLoading = false,
                                isSuccess = true,
                                messageError = String.Empty,
                            ),
                        )
                    },
                    onFailure = { exception ->
                        _loginState.emit(
                            LoginUiState(
                                isSuccess = false,
                                isLoading = false,
                                messageError = exception.message ?: String.Empty,
                            ),
                        )
                    },
                )
            }.onStart {
                _loginState.emit(
                    LoginUiState(
                        isSuccess = false,
                        isLoading = true,
                        messageError = String.Empty,
                    ),
                )
            }.flowOn(ioDispatcher).launchIn(viewModelScope)
        }

        fun setEmail(email: Pair<String, Boolean>) {
            loginUserInfo = loginUserInfo.copy(email = email.first)
            emailState = email.second
        }

        fun setPassword(password: Pair<String, Boolean>) {
            loginUserInfo = loginUserInfo.copy(password = password.first)
            passwordState = password.second
        }

        fun getEmail(): String = loginUserInfo.email

        fun getPassword(): String = loginUserInfo.password
    }
