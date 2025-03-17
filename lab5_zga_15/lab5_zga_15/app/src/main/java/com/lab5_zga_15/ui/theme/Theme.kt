package com.lab5_zga_15.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.lab5_zga_15.ui.AboutColor
import com.lab5_zga_15.ui.SettingsColor

@Composable
fun Lab5zga15Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        content = content
    )
}

// ✅ Define Light & Dark Themes
private val LightColorScheme = lightColorScheme(
    primary = SettingsColor,
    secondary = AboutColor
)

private val DarkColorScheme = darkColorScheme(
    primary = SettingsColor,
    secondary = AboutColor
)
