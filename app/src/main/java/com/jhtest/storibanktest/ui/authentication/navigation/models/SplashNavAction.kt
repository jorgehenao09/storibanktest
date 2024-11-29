package com.jhtest.storibanktest.ui.authentication.navigation.models

sealed class SplashNavAction : UiAction {
    data object NavigateToLogin : SplashNavAction()
    data object NavigateToHome : SplashNavAction()
}