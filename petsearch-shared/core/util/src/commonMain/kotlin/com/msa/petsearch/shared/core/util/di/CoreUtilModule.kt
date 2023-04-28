package com.msa.petsearch.shared.core.util.di

import com.msa.petsearch.shared.core.util.BaseLogger
import org.koin.dsl.module

val CoreUtilModule = module {
    // See https://github.com/touchlab/Kermit
    factory { (tag: String?) -> if (tag != null) BaseLogger.withTag(tag) else BaseLogger }
}
