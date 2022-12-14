package com.msa.petsearch.shared.core.util

import co.touchlab.kermit.Logger
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

val BaseLogger =
    Logger(
        config = StaticConfig(logWriterList = listOf(platformLogWriter()/*, CrashlyticsLogWriter()*/)),
        tag = "PetFinder-Showcase"
    )

// Simple function to clean up the syntax a bit
fun KoinComponent.injectLogger(tag: String): Lazy<Logger> = inject { parametersOf(tag) }
