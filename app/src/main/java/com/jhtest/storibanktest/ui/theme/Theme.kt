package com.jhtest.storibanktest.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.jhtest.storibanktest.ui.theme.components.utils.Shapes

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    tertiary = TertiaryColor,
    background = BackgroundColor,
    primaryContainer = PrimaryContainerColor,
    secondaryContainer = SecondaryContainerColor,
    tertiaryContainer = TertiaryContainerColor,
    surfaceContainer = SurfaceVariantColor
)

@Composable
fun StoriBankTTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography(),
        shapes = Shapes,
        content = content
    )
}