package com.msa.petsearch.shared.networkinfra.di

import com.msa.petsearch.shared.networkinfra.PetFinderApi
import com.msa.petsearch.shared.networkinfra.testfake.FakePetFinderApiImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val NetworkInfrastructureTestModule = module {
    singleOf(::FakePetFinderApiImpl) bind PetFinderApi::class
}
