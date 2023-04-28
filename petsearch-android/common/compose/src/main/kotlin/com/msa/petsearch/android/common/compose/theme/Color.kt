@file:Suppress("MagicNumber")

package com.msa.petsearch.android.common.compose.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import com.msa.petsearch.shared.resources.SharedR
import dev.icerock.moko.resources.compose.colorResource

val AppColorScheme
    @Composable get() = ColorScheme(
        primary = colorResource(resource = SharedR.colors.onSurface),
        onPrimary = colorResource(resource = SharedR.colors.onSurface),
        primaryContainer = colorResource(resource = SharedR.colors.onSurface),
        onPrimaryContainer = colorResource(resource = SharedR.colors.onSurface),
        inversePrimary = colorResource(resource = SharedR.colors.onSurface),
        secondary = colorResource(resource = SharedR.colors.onSurface),
        onSecondary = colorResource(resource = SharedR.colors.onSurface),
        secondaryContainer = colorResource(resource = SharedR.colors.onSurface),
        onSecondaryContainer = colorResource(resource = SharedR.colors.onSurface),
        tertiary = colorResource(resource = SharedR.colors.onSurface),
        onTertiary = colorResource(resource = SharedR.colors.onSurface),
        tertiaryContainer = colorResource(resource = SharedR.colors.onSurface),
        onTertiaryContainer = colorResource(resource = SharedR.colors.onSurface),
        background = colorResource(resource = SharedR.colors.background),
        onBackground = colorResource(resource = SharedR.colors.onSurface),
        surface = colorResource(resource = SharedR.colors.surface),
        onSurface = colorResource(resource = SharedR.colors.onSurface),
        surfaceVariant = colorResource(resource = SharedR.colors.onSurface),
        onSurfaceVariant = colorResource(resource = SharedR.colors.onSurface),
        surfaceTint = colorResource(resource = SharedR.colors.onSurface),
        inverseSurface = colorResource(resource = SharedR.colors.onSurface),
        inverseOnSurface = colorResource(resource = SharedR.colors.onSurface),
        error = colorResource(resource = SharedR.colors.onSurface),
        onError = colorResource(resource = SharedR.colors.onSurface),
        errorContainer = colorResource(resource = SharedR.colors.onSurface),
        onErrorContainer = colorResource(resource = SharedR.colors.onSurface),
        outline = colorResource(resource = SharedR.colors.onSurface),
        outlineVariant = colorResource(resource = SharedR.colors.onSurface),
        scrim = colorResource(resource = SharedR.colors.onSurface),
    )
