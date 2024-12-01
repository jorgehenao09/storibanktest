package com.jhtest.storibanktest.ui.viewmodels

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jhtest.storibanktest.utils.Empty
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class LoginUserInfo(
    val userName: String = String.Empty,
    val password: String = String.Empty,
)

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {
    private var userNameState by mutableStateOf(false)
    private var passwordState by mutableStateOf(false)

    val isButtonEnabled by derivedStateOf {
        userNameState && passwordState
    }

    private var loginUserInfo = LoginUserInfo()

    fun setPassword(password: Pair<String, Boolean>) {
        loginUserInfo = loginUserInfo.copy(password = password.first)
        passwordState = password.second
    }

    fun getPassword(): String = loginUserInfo.password

    fun setUserName(userName: Pair<String, Boolean>) {
        loginUserInfo = loginUserInfo.copy(userName = userName.first)
        userNameState = userName.second
    }

    fun getUserName(): String = loginUserInfo.userName
}