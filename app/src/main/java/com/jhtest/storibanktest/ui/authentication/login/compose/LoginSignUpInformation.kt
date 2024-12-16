package com.jhtest.storibanktest.ui.authentication.login.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jhtest.storibanktest.R
import com.jhtest.storibanktest.ui.theme.components.DescriptionText
import com.jhtest.storibanktest.ui.theme.components.SecondaryButton

@Composable
internal fun SignUpInformation(
    modifier: Modifier = Modifier,
    onButtonClicked: () -> Unit,
) {
    Column(
        modifier =
            modifier
                .padding(vertical = 24.dp, horizontal = 24.dp),
    ) {
        DescriptionText(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            textValue = stringResource(
                id = R.string.authentication_view_login_sign_up_description_text
            ),
        )

        SecondaryButton(
            modifier = Modifier.padding(vertical = 8.dp),
            textValue = stringResource(id = R.string.label_sign_up),
            onButtonClicked = onButtonClicked,
        )
    }
}
