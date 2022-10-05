package com.msa.petsearch.shared.datainfrastructurepreferences.di

import com.msa.petsearch.shared.datainfrastructurepreferences.service.PreferenceDelegate
import com.russhwolf.settings.Settings
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val PreferenceInfrastructureModule = module {
    // Inject with No-Args constructor
    singleOf(::Settings)

    singleOf(::PreferenceDelegate)
}
