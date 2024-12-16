package com.jhtest.storibanktest.ui.theme.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextErrorComponent(
    modifier: Modifier = Modifier,
    messageError: String,
) {
    Text(
        modifier = modifier.padding(start = 8.dp),
        text = messageError,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.tertiary,
    )
}
