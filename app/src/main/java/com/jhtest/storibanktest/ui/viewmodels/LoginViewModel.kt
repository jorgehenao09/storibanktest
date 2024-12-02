package com.jhtest.storibanktest.ui.viewmodels

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jhtest.storibanktest.ui.authentication.login.states.LoginUiState
import com.jhtest.storibanktest.utils.Empty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class LoginUserInfo(
    val email: String = String.Empty,
    val password: String = String.Empty,
)

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {

    private val _loginState: MutableStateFlow<LoginUiState> =
        MutableStateFlow(LoginUiState())
    val loginState: StateFlow<LoginUiState>
        get() = _loginState.asStateFlow()

    private var emailState by mutableStateOf(false)
    private var passwordState by mutableStateOf(false)

    val isButtonEnabled by derivedStateOf {
        emailState && passwordState
    }

    private var loginUserInfo = LoginUserInfo()

    fun onValidateCredentials() {

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