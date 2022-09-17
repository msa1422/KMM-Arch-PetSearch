package com.msa.petsearch.shared

import com.msa.petsearch.shared.coreutil.di.DomainCoreUtilModule
import com.msa.petsearch.shared.datainfrastructurecache.di.CacheInfrastructureModule
import com.msa.petsearch.shared.networkinfra.di.NetworkInfrastructureModule
import com.msa.petsearch.shared.datainfrastructurepreferences.di.PreferenceInfrastructureModule
import com.msa.petsearch.shared.domain.homeuicontract.di.HomeUiContractModule
import com.msa.petsearch.shared.domain.petdetailuicontract.di.PetDetailUiContractModule
import com.msa.petsearch.shared.repository.home.di.HomeRepositoryModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(modules: List<Module>? = null) = startKoin {
    modules(
        DomainCoreUtilModule,
        NetworkInfrastructureModule,
        CacheInfrastructureModule,
        PreferenceInfrastructureModule,
        HomeRepositoryModule,
        HomeUiContractModule,
        PetDetailUiContractModule
    )
    modules?.let {
        modules(it)
    }
}
