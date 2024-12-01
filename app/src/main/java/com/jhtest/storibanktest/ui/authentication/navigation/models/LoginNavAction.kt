package com.jhtest.storibanktest.ui.authentication.navigation.models

sealed class LoginNavAction : UiAction {
    data object NavigateToHome : SplashNavAction()
    data object NavigateToSignUp : SplashNavAction()
    data object NavigateToErrorScreen : SplashNavAction()
}