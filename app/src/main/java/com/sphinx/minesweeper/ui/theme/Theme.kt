package com.sphinx.minesweeper.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


val Blue500 = Color(0xFF2196F3)
val Blue700 = Color(0xFF1976D2)

private val DarkColorPalette = darkColors(
    primary = Teal700,
    primaryVariant = Teal900,
    background = Color.Black
)

private val LightColorPalette = lightColors(
    primary = Teal500,
    primaryVariant = Teal700,
    secondary = Color(0xFF96000f),
    secondaryVariant = Color(0xFF620000),
    background = Teal900,
    onBackground = Color.White,

    /* Other default colors to override
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MinesweeperTheme(
    darkTheme: Boolean,
    content: @Composable() () -> Unit
) {
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