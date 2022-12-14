package com.msa.petsearch.shared.core.util.extension

fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { split ->
        split.lowercase().replaceFirstChar { char ->
            if (char.isLowerCase()) char.titlecase() else char.toString()
        }
    }
