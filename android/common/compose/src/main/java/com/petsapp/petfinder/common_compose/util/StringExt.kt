package com.petsapp.petfinder.common_compose.util

import androidx.compose.ui.graphics.Color

private const val CHARACTER_RADIX = 16

@Throws(IllegalArgumentException::class, NumberFormatException::class)
fun String.toComposeColor() =
    try {
        Color(removePrefix("#").lowercase().toLong(CHARACTER_RADIX))
    }
    // Way to catch multiple exceptions in one catch. Detekt will show warning here.
    catch (ex: Exception) {
        when (ex) {
            is IllegalArgumentException,
            is NumberFormatException
            -> Color.Unspecified

            else -> throw ex
        }
    }
