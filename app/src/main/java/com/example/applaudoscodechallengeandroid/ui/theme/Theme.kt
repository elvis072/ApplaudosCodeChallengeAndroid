package com.example.applaudoscodechallengeandroid.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple,
    onPrimary = White,
    secondary = Blue,
    tertiary = LightTurquoise,
    background = Black,
    surface = Text,
    onSurface = White,
    onSurfaceVariant = Gray,
    error = Red
)

private val LightColorScheme = lightColorScheme(
    primary = Purple,
    onPrimary = White,
    secondary = Blue,
    tertiary = Turquoise,
    background = Background,
    surface = Gray,
    onSurface = Text,
    onSurfaceVariant = SubText,
    error = Red
)

@Composable
fun ApplaudosCodeChallengeAndroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.apply {
                statusBarColor = colorScheme.secondary.toArgb()
                WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = false
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}