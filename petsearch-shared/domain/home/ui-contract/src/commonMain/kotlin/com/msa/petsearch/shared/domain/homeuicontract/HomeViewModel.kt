package com.msa.petsearch.shared.domain.homeuicontract

import com.msa.petsearch.shared.coreutil.sharedviewmodel.BaseViewModel
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.RouteNavigator
import com.msa.petsearch.shared.coreutil.sharedviewmodel.util.GlobalEvent
import com.msa.petsearch.shared.coreutil.sharedviewmodel.util.ReduxGeneric
import com.msa.petsearch.shared.domain.homeuicontract.contract.HomeProcessor
import com.msa.petsearch.shared.domain.homeuicontract.contract.HomeUpdater
import com.msa.petsearch.shared.domain.homeuicontract.contract.mapper.HomeStateMapper
import com.msa.petsearch.shared.domain.homeuicontract.contract.store.*

class HomeViewModel(updater: HomeUpdater, processor: HomeProcessor, routeNavigator: RouteNavigator):
    BaseViewModel<HomeAction, HomeState, HomeSideEffect, HomeRenderState, HomeNavigation, GlobalEvent, ReduxGeneric.Error>(
        updater = updater,
        initialState = HomeState(),
        processor = processor,
        stateMapper = HomeStateMapper,
        routeNavigator = routeNavigator
    )
