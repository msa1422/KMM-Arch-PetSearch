@file:Suppress("MagicNumber")

package com.msa.petsearch.android.common.compose.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.msa.petsearch.shared.resources.SharedR

private val backgroundLight = Color(SharedR.colors.background_light.color.argb)
private val surfaceLight = Color(SharedR.colors.surface_light.color.argb)
private val onBackgroundLight = Color(SharedR.colors.onSurface_light.color.argb)

private val backgroundDark = Color(SharedR.colors.background_dark.color.argb)
private val surfaceDark = Color(SharedR.colors.surface_dark.color.argb)
private val onBackgroundDark = Color(SharedR.colors.onSurface_dark.color.argb)

internal val AppColorSchemeLight = lightColorScheme(
    primary = onBackgroundLight,
    secondaryContainer = onBackgroundLight,
    onSecondaryContainer = onBackgroundLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onBackgroundLight,
    onSurfaceVariant = onBackgroundLight.copy(alpha = 0.75F),
    onTertiary = onBackgroundLight.copy(alpha = 0.25F)
)

internal val AppColorSchemeDark = darkColorScheme(
    primary = onBackgroundDark,
    secondaryContainer = onBackgroundDark,
    onSecondaryContainer = onBackgroundDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onBackgroundDark,
    onSurfaceVariant = onBackgroundDark.copy(alpha = 0.75F),
    onTertiary = onBackgroundDark.copy(alpha = 0.25F)
)
