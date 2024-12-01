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
    isButtonEnabled: Boolean,
    textValue: String = stringResource(id = R.string.label_continue),
    onButtonClicked: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp)
            .height(48.dp),
        shape = Shapes.medium,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        enabled = isButtonEnabled,
        onClick = onButtonClicked
    ) {
        Text(
            text = textValue,
            style = MaterialTheme.typography.labelLarge,
            color = if (isButtonEnabled) Color.White else MaterialTheme.colorScheme.primaryContainer,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PrimaryButtonPreview() {
    PrimaryButton(
        isButtonEnabled = true,
        onButtonClicked = {}
    )
}