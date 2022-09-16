package com.msa.petsearch.commoncompose.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Black
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import com.petsapp.petfinder.shared.coreres.CoreR.fonts.Inter

private val InterFontFamily = FontFamily(
    listOf(
        Font(resId = Inter.regular.fontResourceId, weight = Normal),
        Font(resId = Inter.semiBold.fontResourceId, weight = SemiBold),
        Font(resId = Inter.black.fontResourceId, weight = Black)
    )
)

// Source for font size and other parameters
// @see https://m3.material.io/styles/typography/type-scale-tokens
internal val AppTypography = Typography(

    // DISPLAY .....................................................................................
    displayLarge = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = Normal,
        fontSize = 57.sp,
        lineHeight = TextUnit(1.12F, TextUnitType.Em)
    ),
    displayMedium = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = Normal,
        fontSize = 45.sp,
        lineHeight = TextUnit(1.15F, TextUnitType.Em)
    ),
    displaySmall = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = Normal,
        fontSize = 36.sp,
        lineHeight = TextUnit(1.22F, TextUnitType.Em)
    ),

    // HEADLINE ....................................................................................
    headlineLarge = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = Normal,
        fontSize = 32.sp,
        lineHeight = TextUnit(1.25F, TextUnitType.Em)
    ),
    headlineMedium = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = Normal,
        fontSize = 28.sp,
        lineHeight = TextUnit(1.285F, TextUnitType.Em)
    ),
    headlineSmall = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = Normal,
        fontSize = 24.sp,
        lineHeight = TextUnit(1.33F, TextUnitType.Em)
    ),

    // TITLE .......................................................................................
    titleLarge = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = Black,
        fontSize = 22.sp,
        lineHeight = TextUnit(1.27F, TextUnitType.Em)
    ),
    titleMedium = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = SemiBold,
        fontSize = 16.sp,
        lineHeight = TextUnit(1.5F, TextUnitType.Em),
        letterSpacing = TextUnit(0.009f, TextUnitType.Em)
    ),
    titleSmall = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = SemiBold,
        fontSize = 14.sp,
        lineHeight = TextUnit(1.42F, TextUnitType.Em),
        letterSpacing = TextUnit(0.007f, TextUnitType.Em)
    ),

    // LABEL .......................................................................................
    labelLarge = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = SemiBold,
        fontSize = 16.sp,
        lineHeight = TextUnit(1.42F, TextUnitType.Em),
        letterSpacing = TextUnit(0.007f, TextUnitType.Em)
    ),
    labelMedium = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = SemiBold,
        fontSize = 14.sp,
        lineHeight = TextUnit(1.33F, TextUnitType.Em),
        letterSpacing = TextUnit(0.03f, TextUnitType.Em)
    ),
    labelSmall = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = SemiBold,
        fontSize = 11.sp,
        lineHeight = TextUnit(1.45F, TextUnitType.Em),
        letterSpacing = TextUnit(0.045f, TextUnitType.Em)
    ),

    // BODY ........................................................................................
    bodyLarge = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = SemiBold,
        fontSize = 16.sp,
        lineHeight = TextUnit(1.5F, TextUnitType.Em),
        letterSpacing = TextUnit(0.03f, TextUnitType.Em)
    ),
    bodyMedium = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = Normal,
        fontSize = 14.sp,
        lineHeight = TextUnit(1.42F, TextUnitType.Em),
        letterSpacing = TextUnit(0.015f, TextUnitType.Em)
    ),
    bodySmall = TextStyle(
        fontFamily = InterFontFamily,
        fontWeight = Normal,
        fontSize = 12.sp,
        lineHeight = TextUnit(1.33F, TextUnitType.Em),
        letterSpacing = TextUnit(0.033f, TextUnitType.Em)
    )
)
