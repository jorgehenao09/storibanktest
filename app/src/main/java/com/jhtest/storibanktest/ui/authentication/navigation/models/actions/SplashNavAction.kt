package com.jhtest.storibanktest.ui.authentication.navigation.models.actions

sealed class SplashNavAction : UiAction {
    data object NavigateToLogin : SplashNavAction()
    data object NavigateToHome : SplashNavAction()
}