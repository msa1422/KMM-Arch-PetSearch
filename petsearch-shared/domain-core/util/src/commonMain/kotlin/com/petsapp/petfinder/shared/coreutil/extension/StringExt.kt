package com.petsapp.petfinder.shared.coreutil.extension

fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { split ->
        split.lowercase().replaceFirstChar { char ->
            if (char.isLowerCase()) char.titlecase() else char.toString()
        }
    }
