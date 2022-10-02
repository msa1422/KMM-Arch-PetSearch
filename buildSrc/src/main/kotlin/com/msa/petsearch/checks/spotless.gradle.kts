package com.msa.petsearch.checks

import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin

apply<SpotlessPlugin>()

@Suppress("INACCESSIBLE_TYPE")
configure<SpotlessExtension> {
    format("misc") {
        target(
            fileTree(
                mapOf(
                    "dir" to ".",
                    "include" to listOf("**/.gitignore", "**/*.yaml", "**/*.yml"),
                    "exclude" to listOf(
                        ".gradle/**",
                        ".gradle-cache/**",
                        "**/tools/**",
                        "**/build/**"
                    )
                )
            )
        )
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    format("xml") {
        target("**/res/**/*.xml")
        indentWithSpaces(4)
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlin {
        target(
            fileTree(
                mapOf(
                    "dir" to ".",
                    "include" to listOf("**/*.kt"),
                    "exclude" to listOf("**/build/**", "**/buildSrc/**", "**/.*")
                )
            )
        )
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    kotlinGradle {
        target(
            fileTree(
                mapOf(
                    "dir" to ".",
                    "include" to listOf("**/*.gradle.kts", "*.gradle.kts"),
                    "exclude" to listOf("**/build/**")
                )
            )
        )

        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
}
