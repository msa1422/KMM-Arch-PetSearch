package com.petsapp.petfinder

import com.petsapp.petfinder.shared.coreutil.di.DomainCoreCommonUtilModule
import com.petsapp.petfinder.shared.datainfrastructurecache.di.CacheInfrastructureModule
import com.petsapp.petfinder.shared.datainfrastructurenetwork.di.NetworkInfrastructureModule
import com.petsapp.petfinder.shared.datainfrastructurepreferences.di.PreferenceInfrastructureModule
import com.petsapp.petfinder.shared.domain.homeuicontract.di.HomeUiContractModule
import com.petsapp.petfinder.shared.domain.petdetailuicontract.di.PetDetailUiContractModule
import com.petsapp.petfinder.shared.repository.home.di.HomeRepositoryModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(modules: List<Module>? = null) = startKoin {
    modules(
        DomainCoreCommonUtilModule,
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
