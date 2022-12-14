package com.msa.petsearch.shared.data.infra.preferences.di

import com.msa.petsearch.shared.data.infra.preferences.service.PreferenceDelegate
import com.russhwolf.settings.Settings
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val DataInfraPreferenceModule = module {
    // Inject with No-Args constructor
    singleOf(::Settings)

    singleOf(::PreferenceDelegate)
}
