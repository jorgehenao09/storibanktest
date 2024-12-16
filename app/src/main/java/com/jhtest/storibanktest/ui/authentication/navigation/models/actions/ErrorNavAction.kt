package com.jhtest.storibanktest.ui.authentication.navigation.models.actions

sealed class ErrorNavAction : UiAction {
    data object NavigateBack : SuccessNavAction()
}
