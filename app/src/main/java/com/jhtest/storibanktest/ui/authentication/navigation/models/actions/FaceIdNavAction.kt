package com.jhtest.storibanktest.ui.authentication.navigation.models.actions

sealed class FaceIdNavAction : UiAction {
    data object NavigateToErrorScreen : FaceIdNavAction()
    data object NavigateToSuccessScreen : FaceIdNavAction()
}
