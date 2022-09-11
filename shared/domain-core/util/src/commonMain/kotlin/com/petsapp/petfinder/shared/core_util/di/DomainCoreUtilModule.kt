package com.petsapp.petfinder.shared.core_util.di

import com.petsapp.petfinder.shared.core_util.BaseLogger
import com.petsapp.petfinder.shared.core_util.resource.MessageDeque
import com.petsapp.petfinder.shared.core_util.resource.MessageDequeParameter
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.navigation.RouteNavigator
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.navigation.RouteNavigatorImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val DomainCoreCommonUtilModule = module {

    singleOf(::RouteNavigatorImpl) bind RouteNavigator::class

    singleOf(::MessageDequeParameter)
    singleOf(::MessageDeque)

    // See https://github.com/touchlab/Kermit
    factory { (tag: String?) -> if (tag != null) BaseLogger.withTag(tag) else BaseLogger }
}
