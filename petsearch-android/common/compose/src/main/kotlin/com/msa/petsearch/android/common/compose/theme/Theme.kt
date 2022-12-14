package com.msa.petsearch.android.common.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun ApplicationTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) AppColorSchemeDark else AppColorSchemeLight,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
