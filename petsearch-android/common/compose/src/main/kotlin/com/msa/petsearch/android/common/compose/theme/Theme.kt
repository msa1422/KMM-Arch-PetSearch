package com.msa.petsearch.android.common.compose.theme

import android.os.Build
import android.os.Build.VERSION_CODES.Q
import androidx.activity.ComponentActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun ApplicationTheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    val window = (view.context as ComponentActivity).window
    val windowsInsetsController = WindowCompat.getInsetsController(window, view)
    val darkTheme = isSystemInDarkTheme()
    val transparentNav = darkTheme || Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    val transparentStatus = darkTheme || Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    val colorTransparent = Color.Transparent.toArgb()
    val colorBlackTranslucent = Color.Black.copy(alpha = 0.2F).toArgb()

    WindowCompat.setDecorFitsSystemWindows(window, false)

    window.statusBarColor = if (transparentStatus) colorTransparent else colorBlackTranslucent
    window.navigationBarColor = if (transparentNav) colorTransparent else  colorBlackTranslucent

    windowsInsetsController.isAppearanceLightStatusBars = !darkTheme
    windowsInsetsController.isAppearanceLightNavigationBars = !darkTheme

    if (Build.VERSION.SDK_INT >= Q) window.isNavigationBarContrastEnforced = false

    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
