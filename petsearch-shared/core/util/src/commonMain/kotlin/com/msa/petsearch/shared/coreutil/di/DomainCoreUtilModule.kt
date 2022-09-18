package com.msa.petsearch.shared.coreutil.di

import com.msa.petsearch.shared.coreutil.BaseLogger
import com.msa.petsearch.shared.coreutil.resource.MessageDeque
import com.msa.petsearch.shared.coreutil.resource.MessageDequeParameter
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.RouteNavigator
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.RouteNavigatorImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val DomainCoreUtilModule = module {

    singleOf(::RouteNavigatorImpl) bind RouteNavigator::class

    singleOf(::MessageDequeParameter)
    singleOf(::MessageDeque)

    // See https://github.com/touchlab/Kermit
    factory { (tag: String?) -> if (tag != null) BaseLogger.withTag(tag) else BaseLogger }
}
