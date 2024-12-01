package com.jhtest.storibanktest.ui.authentication.login.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.jhtest.storibanktest.R
import com.jhtest.storibanktest.ui.theme.components.DescriptionText
import com.jhtest.storibanktest.ui.theme.components.TextFieldValidation
import com.jhtest.storibanktest.ui.theme.components.TextFieldScreen
import com.jhtest.storibanktest.ui.theme.components.utils.formModifier
import com.jhtest.storibanktest.ui.viewmodels.LoginViewModel

@Composable
fun LoginForm(
    loginViewModel: LoginViewModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {

        DescriptionText(
            modifier = Modifier.formModifier(),
            textValue = stringResource(id = R.string.authentication_view_description_text)
        )

        TextFieldScreen(
            modifier = Modifier.formModifier(),
            label = stringResource(id = R.string.authentication_view_textfield_user_name),
            defaultValue = loginViewModel.getUserName(),
            validationType = TextFieldValidation.USER_NAME,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            onTextChange = {
                loginViewModel.setUserName(it)
            }
        )
        TextFieldScreen(
            modifier = Modifier.formModifier(),
            label = stringResource(id = R.string.authentication_view_textfield_password),
            defaultValue = loginViewModel.getPassword(),
            validationType = TextFieldValidation.PASSWORD,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            onTextChange = {
                loginViewModel.setPassword(it)
            }
        )
    }
}