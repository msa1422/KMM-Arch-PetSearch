package com.petsapp.petfinder

import com.petsapp.petfinder.shared.core_util.di.DomainCoreCommonUtilModule
import com.petsapp.petfinder.shared.data_infrastructure_network.di.NetworkInfrastructureModule
import com.petsapp.petfinder.shared.domain.home_ui_contract.di.HomeUiContractModule
import com.petsapp.petfinder.shared.domain.pet_detail_ui_contract.di.PetDetailUiContractModule
import com.petsapp.petfinder.shared.repository.home.di.HomeRepositoryModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(modules: List<Module>?) = startKoin {
    modules(
        DomainCoreCommonUtilModule,
        NetworkInfrastructureModule,
        HomeRepositoryModule,
        HomeUiContractModule,
        PetDetailUiContractModule
    )
    modules?.let {
        modules(it)
    }
}
