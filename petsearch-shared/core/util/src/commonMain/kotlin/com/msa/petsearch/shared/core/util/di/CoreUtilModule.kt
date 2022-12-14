package com.msa.petsearch.shared.core.util.di

import com.msa.petsearch.shared.core.util.BaseLogger
import com.msa.petsearch.shared.core.util.resource.MessageDeque
import com.msa.petsearch.shared.core.util.resource.MessageDequeParameter
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.RouteNavigator
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.RouteNavigatorImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val CoreUtilModule = module {

    singleOf(::RouteNavigatorImpl) bind RouteNavigator::class

    singleOf(::MessageDequeParameter)
    singleOf(::MessageDeque)

    // See https://github.com/touchlab/Kermit
    factory { (tag: String?) -> if (tag != null) BaseLogger.withTag(tag) else BaseLogger }
}
