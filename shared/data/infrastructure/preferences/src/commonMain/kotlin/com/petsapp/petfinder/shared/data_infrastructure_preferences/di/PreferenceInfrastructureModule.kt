package com.petsapp.petfinder.shared.data_infrastructure_preferences.di

import com.petsapp.petfinder.shared.data_infrastructure_preferences.service.PreferenceDelegate
import com.russhwolf.settings.Settings
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val PreferenceInfrastructureModule = module {
    // Inject with No-Args constructor
    singleOf(::Settings)

    singleOf(::PreferenceDelegate)
}