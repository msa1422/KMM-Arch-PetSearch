package com.msa.petsearch.android.common.compose.theme

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.M
import android.os.Build.VERSION_CODES.O
import android.os.Build.VERSION_CODES.Q
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun ApplicationTheme(
    view: View = LocalView.current,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )

    LaunchedEffect(darkTheme) {
        val window = checkNotNull(view.context as? ComponentActivity).window
        val transparent = Color.Transparent.toArgb()
        val blackTranslucent = Color.Black.copy(alpha = 0.2F).toArgb()
        val windowsInsetsController = WindowCompat.getInsetsController(window, view)

        windowsInsetsController.isAppearanceLightStatusBars = !darkTheme
        windowsInsetsController.isAppearanceLightNavigationBars = !darkTheme

        window.statusBarColor = if (darkTheme || SDK_INT >= M) transparent else blackTranslucent
        window.navigationBarColor = if (darkTheme || SDK_INT >= O) transparent else blackTranslucent

        WindowCompat.setDecorFitsSystemWindows(window, false)

        if (SDK_INT >= Q) {
            window.isNavigationBarContrastEnforced = false
        }
    }
}
