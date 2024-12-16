package com.jhtest.storibanktest.ui.authentication.login.states

import com.jhtest.storibanktest.utils.Empty

data class LoginUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val messageError: String = String.Empty,
)
