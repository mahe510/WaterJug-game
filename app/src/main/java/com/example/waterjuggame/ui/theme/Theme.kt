package com.example.waterjuggame.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AppColorScheme = lightColorScheme(
    primary = AquaBlue,
    secondary = AquaDeep,
    background = BackgroundSoft,
    surface = CardSoft,

    onPrimary = TextDark,
    onBackground = TextDark,
    onSurface = TextDark
)

@Composable
fun WaterJugTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography,
        content = content
    )
}
