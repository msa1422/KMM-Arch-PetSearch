package com.msa.petsearch.commoncompose.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.msa.petsearch.shared.resources.SharedR

private val backgroundGray = Color(SharedR.colors.background.color.argb)
private val surfaceWhite = Color(SharedR.colors.surface.color.argb)
private val onBackground = Color(SharedR.colors.onSurface.color.argb)

internal val AppColorScheme = lightColorScheme(
    primary = onBackground,
    secondaryContainer = onBackground,
    onSecondaryContainer = onBackground,
    background = backgroundGray,
    onBackground = onBackground,
    surface = surfaceWhite,
    onSurface = onBackground,
    onSurfaceVariant = onBackground.copy(alpha = 0.75F),
    onTertiary = onBackground.copy(alpha = 0.25F)
)
