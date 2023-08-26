package com.msa.petsearch.shared

import com.msa.petsearch.shared.core.util.di.CoreUtilModule
import com.msa.petsearch.shared.data.source.di.DataSourceModule
import com.msa.petsearch.shared.ui.home.di.SharedUiHomeModule
import com.msa.petsearch.shared.ui.petdetail.di.SharedUiPetDetailModule
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.startKoin
import org.koin.core.lazyModules
import org.koin.core.module.Module

@OptIn(KoinExperimentalAPI::class)
fun initKoin(
    eagerModules: List<Module>? = null,
    lazyModules: List<Lazy<Module>>? = null
) = startKoin {
    lazyModules(
        CoreUtilModule,
        DataSourceModule,
        SharedUiHomeModule,
        SharedUiPetDetailModule
    )

    eagerModules?.let(::modules)
    lazyModules?.let(::lazyModules)
}
