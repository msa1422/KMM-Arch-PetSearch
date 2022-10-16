package com.msa.petsearch.commoncompose.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun ApplicationTheme(isSystemInDarkTheme: Boolean, content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme) AppColorSchemeDark else AppColorSchemeLight,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
