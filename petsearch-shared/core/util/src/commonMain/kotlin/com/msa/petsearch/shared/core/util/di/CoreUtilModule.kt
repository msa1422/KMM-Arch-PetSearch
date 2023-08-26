package com.msa.petsearch.shared.core.util.di

import com.msa.petsearch.shared.core.util.BaseLogger
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.lazyModule

@OptIn(KoinExperimentalAPI::class)
val CoreUtilModule = lazyModule {
    // See https://github.com/touchlab/Kermit
    factory { (tag: String?) -> if (tag != null) BaseLogger.withTag(tag) else BaseLogger }
}
