package com.jhtest.storibanktest.ui.viewmodels

import android.net.Uri
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhtest.storibanktest.di.IoDispatcher
import com.jhtest.storibanktest.domain.SignUpUC
import com.jhtest.storibanktest.ui.authentication.signUp.states.SignUpUiState
import com.jhtest.storibanktest.utils.Empty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class SignUpUserInfo(
    val userId: String = String.Empty,
    val name: String = String.Empty,
    val lastName: String = String.Empty,
    val email: String = String.Empty,
    val password: String = String.Empty,
    val faceId: Uri? = null,
)

@HiltViewModel
class SignUpViewModel
    @Inject
    constructor(
        private val signUpUC: SignUpUC,
        @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    ) : ViewModel() {
        private val _signUpState: MutableStateFlow<SignUpUiState> =
            MutableStateFlow(SignUpUiState())
        val signUpState: StateFlow<SignUpUiState>
            get() = _signUpState.asStateFlow()

        private var nameState by mutableStateOf(false)
        private var lastNameState by mutableStateOf(false)
        private var emailState by mutableStateOf(false)
        private var passwordState by mutableStateOf(false)
        private var faceIdState by mutableStateOf(false)

        val isContinueButtonEnabled by derivedStateOf {
            emailState && passwordState && nameState && lastNameState
        }

        val isSignUpButtonEnabled by derivedStateOf {
            isContinueButtonEnabled && faceIdState
        }

        private var signUpUserInfo = SignUpUserInfo()

        fun onSignUp() {
            signUpUC.invoke(signUpUserInfo).map { result ->
                result.fold(
                    onSuccess = {
                        _signUpState.update { state ->
                            state.copy(
                                isLoading = false,
                                isSuccess = true,
                            )
                        }
                    },
                    onFailure = { exception ->
                        _signUpState.update { state ->
                            state.copy(
                                isLoading = false,
                                messageError = exception.message ?: String.Empty,
                            )
                        }
                    },
                )
            }.onStart {
                _signUpState.update { state ->
                    state.copy(isLoading = true)
                }
            }.flowOn(ioDispatcher).launchIn(viewModelScope)
        }

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

        fun setFaceId(faceId: Pair<Uri, Boolean>) {
            signUpUserInfo = signUpUserInfo.copy(faceId = faceId.first)
            faceIdState = faceId.second
        }

        fun getName(): String = signUpUserInfo.name

        fun getLastName(): String = signUpUserInfo.lastName

        fun getEmail(): String = signUpUserInfo.email

        fun getPassword(): String = signUpUserInfo.password
    }
