package com.petsapp.petfinder.common_compose.util

import androidx.compose.ui.graphics.Color

@Throws(IllegalArgumentException::class, NumberFormatException::class)
fun String.toComposeColor() =
    try {
        Color(removePrefix("#").lowercase().toLong(16))
    }
    catch (ex: Exception) {
        when (ex) {
            is IllegalArgumentException,
            is NumberFormatException
            -> Color.Unspecified

            else -> throw ex
        }
    }
