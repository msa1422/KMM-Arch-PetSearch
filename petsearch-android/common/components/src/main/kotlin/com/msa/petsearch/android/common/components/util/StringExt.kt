package com.msa.petsearch.android.common.components.util

import androidx.compose.ui.graphics.Color

private const val CHARACTER_RADIX = 16

@Throws(IllegalArgumentException::class, NumberFormatException::class)
fun String.toComposeColor() = Color(removePrefix("#").lowercase().toLong(CHARACTER_RADIX))
