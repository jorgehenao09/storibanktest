package com.jhtest.storibanktest.ui.authentication.navigation.models.actions

sealed class SuccessNavAction : UiAction {
    data object NavigateToHome : SuccessNavAction()
}
