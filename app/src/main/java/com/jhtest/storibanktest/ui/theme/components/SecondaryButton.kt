package com.jhtest.storibanktest.ui.theme.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jhtest.storibanktest.ui.theme.components.utils.Shapes

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    isButtonEnabled: Boolean = true,
    textValue: String,
    onButtonClicked: () -> Unit
) {
    TextButton(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = Shapes.medium,
        enabled = isButtonEnabled,
        onClick = { onButtonClicked() }
    ) {
        Text(
            text = textValue,
            style = MaterialTheme.typography.labelLarge,
            color = if (isButtonEnabled )MaterialTheme.colorScheme.primary else Color.Gray,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SecondaryButtonPreview() {
    SecondaryButton(
        textValue = "Sign Up",
        onButtonClicked = {}
    )
}