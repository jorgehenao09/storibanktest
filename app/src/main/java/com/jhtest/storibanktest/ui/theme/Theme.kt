package com.jhtest.storibanktest.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
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
    surfaceVariant = SurfaceVariantColor
)

@Composable
fun StoriBankTTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography(),
        shapes = Shapes,
        content = content
    )
}