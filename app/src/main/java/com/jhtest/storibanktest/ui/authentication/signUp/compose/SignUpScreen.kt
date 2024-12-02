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
import com.jhtest.storibanktest.ui.authentication.navigation.models.UiAction
import com.jhtest.storibanktest.ui.viewmodels.SignUpViewModel

@Composable
internal fun SignUpScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    onAction: (UiAction) -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        val (header, form) = createRefs()

        SignUpHeader(
            modifier = Modifier.constrainAs(header) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }.padding(top = 24.dp)
        )

        SignUpForm(
            signUpViewModel = signUpViewModel,
            modifier = Modifier.constrainAs(form) {
                top.linkTo(header.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SignUpScreen() {
    SignUpScreen {

    }
}