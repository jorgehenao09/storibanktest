package com.jhtest.storibanktest.ui.theme.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DescriptionText(
    modifier: Modifier = Modifier,
    textValue: String
) {
    Text(
        modifier = modifier,
        style = MaterialTheme.typography.labelMedium,
        text = textValue,
        color = MaterialTheme.colorScheme.secondaryContainer
    )
}

@Preview(showSystemUi = true)
@Composable
private fun DescriptionTextPreview() {
    DescriptionText(
        modifier = Modifier,
        "Testing text"
    )
}