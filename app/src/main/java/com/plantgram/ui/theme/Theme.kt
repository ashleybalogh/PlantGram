package com.plantgram.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = PlantGreenPrimary,
    onPrimary = Color.White,
    primaryContainer = PlantGreenLight,
    onPrimaryContainer = Color(0xFF002000),
    secondary = LeafTeal,
    onSecondary = Color.White,
    secondaryContainer = LeafTealLight,
    onSecondaryContainer = Color(0xFF002020),
    background = BackgroundLight,
    onBackground = Color(0xFF1A1C1E),
    surface = SurfaceLight,
    onSurface = Color(0xFF1A1C1E),
    error = ErrorRed,
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = PlantGreenLight,
    onPrimary = Color(0xFF003300),
    primaryContainer = PlantGreenPrimary,
    onPrimaryContainer = Color(0xFFB8F397),
    secondary = LeafTealLight,
    onSecondary = Color(0xFF003731),
    secondaryContainer = LeafTeal,
    onSecondaryContainer = Color(0xFF7FF8D4),
    background = BackgroundDark,
    onBackground = Color(0xFFE2E2E5),
    surface = SurfaceDark,
    onSurface = Color(0xFFE2E2E5),
    error = ErrorRedDark,
    onError = Color(0xFF601410)
)

@Composable
fun PlantGramTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = PlantGramTypography,
        shapes = PlantGramShapes,
        content = content
    )
}