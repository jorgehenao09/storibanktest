package com.jhtest.storibanktest.ui.authentication.login.compose

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jhtest.storibanktest.R
import com.jhtest.storibanktest.ui.authentication.login.states.LoginUiState
import com.jhtest.storibanktest.ui.authentication.navigation.models.actions.LoginNavAction
import com.jhtest.storibanktest.ui.authentication.navigation.models.actions.UiAction
import com.jhtest.storibanktest.ui.theme.components.PrimaryButton
import com.jhtest.storibanktest.ui.viewmodels.LoginViewModel

@Composable
internal fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onAction: (UiAction) -> Unit
) {
    val loginUiState by loginViewModel.loginState.collectAsStateWithLifecycle(
        LoginUiState()
    )

    BackHandler {}

    LaunchedEffect(loginUiState.isSuccess) {
        if (loginUiState.isSuccess) {
            onAction(LoginNavAction.NavigateToHome)
        }
    }

    LaunchedEffect(loginUiState.messageError) {
        if (loginUiState.messageError.isNotEmpty()) {
            onAction(LoginNavAction.NavigateToErrorScreen)
        }
    }

    AnimatedVisibility(loginUiState.isLoading) {
        LoginShimmer()
    }

    AnimatedVisibility(loginUiState.isLoading.not()) {
        LoginContent(loginViewModel) {
            onAction(it)
        }
    }

}

@Composable
private fun LoginContent(
    loginViewModel: LoginViewModel,
    onAction: (UiAction) -> Unit
) {
    val isButtonEnabled = loginViewModel.isButtonEnabled

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        val (header, form, loginButton, signUp) = createRefs()

        Image(
            modifier = Modifier
                .constrainAs(header) {
                    top.linkTo(parent.top, 50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(200.dp),
            painter = painterResource(id = R.drawable.ic_stori_logo),
            contentScale = ContentScale.Fit,
            contentDescription = "logo"
        )

        LoginForm(
            loginViewModel = loginViewModel,
            modifier = Modifier.constrainAs(form) {
                top.linkTo(header.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        PrimaryButton(
            modifier = Modifier.constrainAs(loginButton) {
                top.linkTo(form.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            isButtonEnabled = isButtonEnabled
        ) {
            loginViewModel.onSignInUser()
        }

        SignUpInformation(
            modifier = Modifier.constrainAs(signUp) {
                top.linkTo(loginButton.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            onAction(LoginNavAction.NavigateToSignUp)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        loginViewModel = hiltViewModel(),
        onAction = {}
    )
}