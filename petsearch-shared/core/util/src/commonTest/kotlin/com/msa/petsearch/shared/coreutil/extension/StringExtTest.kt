package com.msa.petsearch.shared.coreutil.extension

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.string.shouldBeUpperCase

@Suppress("UNUSED")
internal class StringExtTest : FunSpec({
    context("Should capitalize each word") {
        withData(
            "These are some test strings excited to get tested.",
            "This is a string with some ^&*0 and /;'.",
            "And this is a string with &[], \\j, #, @."
        ) { testStr ->
            testStr.capitalizeWords().also {
                it.split(" ").forEach { split ->
                    split.first().toString().shouldBeUpperCase()
                }
            }
        }
    }
})
