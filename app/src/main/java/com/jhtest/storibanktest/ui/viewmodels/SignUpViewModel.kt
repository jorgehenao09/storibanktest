package com.jhtest.storibanktest.ui.viewmodels

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jhtest.storibanktest.utils.Empty
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class SignUpUserInfo(
    val name: String = String.Empty,
    val lastName: String = String.Empty,
    val email: String = String.Empty,
    val password: String = String.Empty,
)

@HiltViewModel
class SignUpViewModel @Inject constructor(

) : ViewModel() {

    private var nameState by mutableStateOf(false)
    private var lastNameState by mutableStateOf(false)
    private var emailState by mutableStateOf(false)
    private var passwordState by mutableStateOf(false)

    val isButtonEnabled by derivedStateOf {
        emailState && passwordState && nameState && lastNameState
    }

    private var signUpUserInfo = SignUpUserInfo()

    fun setName(name: Pair<String, Boolean>) {
        signUpUserInfo = signUpUserInfo.copy(name = name.first)
        nameState = name.second
    }

    fun setLastName(lastName: Pair<String, Boolean>) {
        signUpUserInfo = signUpUserInfo.copy(lastName = lastName.first)
        lastNameState = lastName.second
    }

    fun setEmail(email: Pair<String, Boolean>) {
        signUpUserInfo = signUpUserInfo.copy(email = email.first)
        emailState = email.second
    }

    fun setPassword(password: Pair<String, Boolean>) {
        signUpUserInfo = signUpUserInfo.copy(password = password.first)
        passwordState = password.second
    }

    fun getName(): String = signUpUserInfo.name

    fun getLastName(): String = signUpUserInfo.lastName

    fun getEmail(): String = signUpUserInfo.email

    fun getPassword(): String = signUpUserInfo.password
}