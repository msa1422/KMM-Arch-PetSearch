package com.msa.petsearch.shared.prefsinfra.di

import com.msa.petsearch.shared.prefsinfra.service.PreferenceDelegate
import com.russhwolf.settings.Settings
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val PreferenceInfrastructureModule = module {
    // Inject with No-Args constructor
    singleOf(::Settings)

    singleOf(::PreferenceDelegate)
}
