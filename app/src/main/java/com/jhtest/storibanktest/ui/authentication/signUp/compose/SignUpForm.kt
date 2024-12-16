package com.jhtest.storibanktest.ui.authentication.signUp.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.jhtest.storibanktest.R
import com.jhtest.storibanktest.ui.theme.components.TextFieldScreen
import com.jhtest.storibanktest.ui.theme.components.TextFieldValidation
import com.jhtest.storibanktest.ui.theme.components.utils.formModifier
import com.jhtest.storibanktest.ui.viewmodels.SignUpViewModel

@Composable
fun SignUpForm(
    signUpViewModel: SignUpViewModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        TextFieldScreen(
            modifier = Modifier.formModifier(),
            label = stringResource(id = R.string.authentication_view_textfield_name),
            defaultValue = signUpViewModel.getName(),
            validationType = TextFieldValidation.NAME,
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                ),
            onTextChange = {
                signUpViewModel.setName(it)
            },
        )
        TextFieldScreen(
            modifier = Modifier.formModifier(),
            label = stringResource(id = R.string.authentication_view_textfield_last_name),
            defaultValue = signUpViewModel.getLastName(),
            validationType = TextFieldValidation.LAST_NAME,
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                ),
            onTextChange = {
                signUpViewModel.setLastName(it)
            },
        )
        TextFieldScreen(
            modifier = Modifier.formModifier(),
            label = stringResource(id = R.string.authentication_view_textfield_email),
            defaultValue = signUpViewModel.getEmail(),
            validationType = TextFieldValidation.EMAIL,
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                ),
            onTextChange = {
                signUpViewModel.setEmail(it)
            },
        )
        TextFieldScreen(
            modifier = Modifier.formModifier(),
            label = stringResource(id = R.string.authentication_view_textfield_password),
            defaultValue = signUpViewModel.getPassword(),
            validationType = TextFieldValidation.PASSWORD,
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                ),
            onTextChange = {
                signUpViewModel.setPassword(it)
            },
        )
    }
}
