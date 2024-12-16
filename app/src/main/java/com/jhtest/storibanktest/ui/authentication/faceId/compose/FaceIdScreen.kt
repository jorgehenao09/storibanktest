package com.jhtest.storibanktest.ui.authentication.faceId.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jhtest.storibanktest.R
import com.jhtest.storibanktest.ui.authentication.navigation.models.actions.FaceIdNavAction
import com.jhtest.storibanktest.ui.authentication.navigation.models.actions.UiAction
import com.jhtest.storibanktest.ui.theme.components.PrimaryButton
import com.jhtest.storibanktest.ui.viewmodels.SignUpViewModel

@Composable
fun FaceIdScreen(
    signUpViewModel: SignUpViewModel,
    onAction: (UiAction) -> Unit,
) {
    val signUpState by signUpViewModel.signUpState.collectAsStateWithLifecycle()

    if (signUpState.isLoading) {
        FaceIdShimmer()
    }

    if (signUpState.isLoading.not()) {
        FaceIdContent(signUpViewModel)
    }

    LaunchedEffect(signUpState.isSuccess) {
        if (signUpState.isSuccess) {
            onAction(FaceIdNavAction.NavigateToSuccessScreen)
        }
    }

    LaunchedEffect(signUpState.messageError) {
        if (signUpState.messageError.isNotEmpty()) {
            onAction(FaceIdNavAction.NavigateToErrorScreen)
        }
    }
}

@Composable
private fun FaceIdContent(signUpViewModel: SignUpViewModel) {
    val isSignUpButtonEnabled = signUpViewModel.isSignUpButtonEnabled

    ConstraintLayout(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.primary),
    ) {
        val (header, photoSection, signUpBtn) = createRefs()

        FaceIdHeader(
            modifier =
                Modifier
                    .constrainAs(header) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(top = 84.dp),
        )

        FaceIdPhoto(
            modifier =
                Modifier
                    .constrainAs(photoSection) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(start = 16.dp, end = 16.dp),
            onImageCaptured = { uri ->
                signUpViewModel.setFaceId(uri to true)
            },
        )

        PrimaryButton(
            modifier =
                Modifier.constrainAs(signUpBtn) {
                    bottom.linkTo(parent.bottom, 54.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            isButtonEnabled = isSignUpButtonEnabled,
            textValue = stringResource(R.string.label_finish),
            buttonColor = MaterialTheme.colorScheme.background,
            disabledButtonColor = MaterialTheme.colorScheme.primaryContainer,
            textColor = MaterialTheme.colorScheme.primary,
            onButtonClicked = {
                signUpViewModel.onSignUp()
            },
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun FaceIdScreenPreview() {
    FaceIdScreen(
        signUpViewModel = hiltViewModel<SignUpViewModel>(),
    ) {}
}
