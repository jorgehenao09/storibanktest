package com.jhtest.storibanktest.ui.theme.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jhtest.storibanktest.R
import com.jhtest.storibanktest.ui.theme.components.utils.Shapes

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    isButtonEnabled: Boolean = true,
    textValue: String = stringResource(id = R.string.label_continue),
    buttonColor: Color = MaterialTheme.colorScheme.primary,
    disabledButtonColor: Color? = null,
    textColor: Color = Color.White,
    onButtonClicked: () -> Unit,
) {
    Button(
        modifier =
        modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp)
            .height(48.dp),
        shape = Shapes.medium,
        colors =
        ButtonDefaults.buttonColors(
            containerColor = if (isButtonEnabled) buttonColor
            else MaterialTheme.colorScheme.primaryContainer,
            disabledContainerColor = disabledButtonColor.takeIf { it != null } ?: Color.Unspecified,
        ),
        enabled = isButtonEnabled,
        onClick = onButtonClicked,
    ) {
        Text(
            text = textValue,
            style = MaterialTheme.typography.labelLarge,
            color = if (isButtonEnabled) textColor else MaterialTheme.colorScheme.secondaryContainer,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PrimaryButtonPreview() {
    PrimaryButton(
        isButtonEnabled = false,
        onButtonClicked = {},
    )
}
