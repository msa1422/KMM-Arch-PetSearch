package com.msa.petsearch.shared.domain.homeuicontract

import com.msa.petsearch.shared.coreutil.resource.MessageDeque
import com.msa.petsearch.shared.coreutil.sharedviewmodel.BaseViewModel
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.RouteNavigator
import com.msa.petsearch.shared.coreutil.sharedviewmodel.model.GlobalEvent
import com.msa.petsearch.shared.coreutil.sharedviewmodel.model.ReduxGeneric
import com.msa.petsearch.shared.domain.homeuicontract.contract.HomeProcessor
import com.msa.petsearch.shared.domain.homeuicontract.contract.HomeUpdater
import com.msa.petsearch.shared.domain.homeuicontract.contract.mapper.HomeStateMapper
import com.msa.petsearch.shared.domain.homeuicontract.contract.store.*

class HomeViewModel
internal constructor(
    updater: HomeUpdater,
    processor: HomeProcessor,
    routeNavigator: RouteNavigator,
    messageDeque: MessageDeque
) :
    BaseViewModel<HomeAction, HomeState, HomeSideEffect, HomeRenderState, HomeNavigation, GlobalEvent, ReduxGeneric.Error>(
        updater = updater,
        initialState = HomeState(),
        processor = processor,
        stateMapper = HomeStateMapper,
        routeNavigator = routeNavigator,
        messageDeque = messageDeque
    )
