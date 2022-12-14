package com.msa.petsearch.shared

import com.msa.petsearch.shared.core.util.di.CoreUtilModule
import com.msa.petsearch.shared.data.infra.cache.di.DataInfraCacheModule
import com.msa.petsearch.shared.data.infra.network.di.DataInfraNetworkModule
import com.msa.petsearch.shared.data.infra.preferences.di.DataInfraPreferenceModule
import com.msa.petsearch.shared.data.source.di.DataSourceModule
import com.msa.petsearch.shared.ui.home.di.SharedUiHomeModule
import com.msa.petsearch.shared.ui.petdetail.di.SharedUiPetDetailModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(modules: List<Module>? = null) = startKoin {
    modules(
        CoreUtilModule,
        DataInfraCacheModule,
        DataInfraNetworkModule,
        DataInfraPreferenceModule,
        DataSourceModule,
        SharedUiHomeModule,
        SharedUiPetDetailModule
    )
    modules?.let(::modules)
}
