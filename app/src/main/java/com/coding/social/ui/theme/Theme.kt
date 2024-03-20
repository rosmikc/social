package com.coding.social.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = darkPrimary,
    primaryVariant = darkPrimaryVarient,
    secondary = darkSecondary,
    background = darkBackground,
    onBackground = darkOnBackground,
    surface = darkSurface,
    onSurface = darkOnSurface,
    onPrimary = darkOnPrimary,
    onSecondary = darkOnSecondary,
)

private val LightColorPalette = lightColors(
    primary = lightPrimary,
    primaryVariant = lightPrimaryVarient,
    secondary = lightSecondary,
    background = lightBackground,
    onBackground = lightOnBackground,
    surface = lightSurface,
    onSurface = lightOnSurface,
    onPrimary = lightOnPrimary,
    onSecondary = lightOnSecondary,
)

@Composable
fun SocialTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}