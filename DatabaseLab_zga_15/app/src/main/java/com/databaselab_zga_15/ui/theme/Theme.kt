package com.databaselab_zga_15.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Define Light and Dark Color Schemes
private val DarkColorScheme = darkColorScheme(
    primary = Blue80,
    secondary = Teal80,
    background = Black,
    onBackground = White
)

private val LightColorScheme = lightColorScheme(
    primary = Blue40,
    secondary = Teal40,
    background = White,
    onBackground = Black
)

// Define Custom Theme Wrapper
@Composable
fun DatabaseLabTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
