package com.jhtest.storibanktest.ui.authentication.faceId.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.jhtest.storibanktest.ui.authentication.navigation.models.actions.UiAction
import com.jhtest.storibanktest.ui.theme.components.PrimaryButton
import com.jhtest.storibanktest.ui.viewmodels.SignUpViewModel

@Composable
fun FaceIdScreen(
    signUpViewModel: SignUpViewModel,
    onAction: (UiAction) -> Unit
) {
    val isSignUpButtonEnabled = signUpViewModel.isSignUpButtonEnabled

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.primary),
    ) {
        val (header, photoSection, signUpBtn) = createRefs()

        FaceIdHeader(
            modifier = Modifier
                .constrainAs(header) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 84.dp)
        )

        FaceIdPhoto(
            modifier = Modifier
                .constrainAs(photoSection) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(start = 16.dp, end = 16.dp)
        )

        PrimaryButton(
            modifier = Modifier.constrainAs(signUpBtn) {
                bottom.linkTo(parent.bottom, 54.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            isButtonEnabled = isSignUpButtonEnabled,
            textValue = "Finalizar",
            buttonColor = MaterialTheme.colorScheme.background,
            disabledButtonColor = MaterialTheme.colorScheme.primaryContainer,
            textColor = MaterialTheme.colorScheme.primary,
            onButtonClicked = {

            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun FaceIdScreenPreview() {
    FaceIdScreen(
        signUpViewModel = hiltViewModel<SignUpViewModel>()
    ) {}
}