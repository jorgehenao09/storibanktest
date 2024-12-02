package com.jhtest.storibanktest.ui.authentication.navigation.models.actions

sealed class SignUpNavAction: UiAction {
    data object NavigateToFaceId : SignUpNavAction()
}