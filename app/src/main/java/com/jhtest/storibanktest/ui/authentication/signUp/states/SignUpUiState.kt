package com.jhtest.storibanktest.ui.authentication.signUp.states

import com.jhtest.storibanktest.utils.Empty

data class SignUpUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val messageError: String = String.Empty,
)
