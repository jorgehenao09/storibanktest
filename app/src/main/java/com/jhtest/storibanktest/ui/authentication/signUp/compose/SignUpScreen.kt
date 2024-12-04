package com.jhtest.storibanktest.ui.authentication.signUp.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhtest.storibanktest.ui.authentication.navigation.models.actions.SignUpNavAction
import com.jhtest.storibanktest.ui.authentication.navigation.models.actions.UiAction
import com.jhtest.storibanktest.ui.theme.components.PrimaryButton
import com.jhtest.storibanktest.ui.viewmodels.SignUpViewModel

@Composable
internal fun SignUpScreen(
    signUpViewModel: SignUpViewModel,
    onAction: (UiAction) -> Unit,
) {
    val isButtonEnabled = signUpViewModel.isContinueButtonEnabled

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        val (header, form, continueBtn) = createRefs()

        SignUpHeader(
            modifier = Modifier
                .constrainAs(header) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 24.dp)
        )

        SignUpForm(
            signUpViewModel = signUpViewModel,
            modifier = Modifier.constrainAs(form) {
                top.linkTo(header.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        PrimaryButton(
            modifier = Modifier.constrainAs(continueBtn) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom, 54.dp)
            },
            isButtonEnabled = isButtonEnabled
        ) {
            onAction(SignUpNavAction.NavigateToFaceId)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(
        signUpViewModel = hiltViewModel<SignUpViewModel>()
    ) { }
}