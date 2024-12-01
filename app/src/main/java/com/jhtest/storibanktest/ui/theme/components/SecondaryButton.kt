package com.jhtest.storibanktest.ui.theme.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jhtest.storibanktest.ui.theme.components.utils.Shapes

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    textValue: String,
    onButtonClicked: () -> Unit
) {
    TextButton(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = Shapes.medium,
        onClick = { onButtonClicked() }
    ) {
        Text(
            text = textValue,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
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