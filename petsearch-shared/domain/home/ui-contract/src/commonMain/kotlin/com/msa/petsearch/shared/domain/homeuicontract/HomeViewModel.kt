package com.msa.petsearch.shared.domain.homeuicontract

import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.BaseViewModel
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.navigation.RouteNavigator
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.util.GlobalEvent
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.util.ReduxGeneric
import com.petsapp.petfinder.shared.domain.homeuicontract.contract.HomeProcessor
import com.petsapp.petfinder.shared.domain.homeuicontract.contract.HomeUpdater
import com.petsapp.petfinder.shared.domain.homeuicontract.contract.mapper.HomeStateMapper
import com.petsapp.petfinder.shared.domain.homeuicontract.contract.store.*

class HomeViewModel(updater: HomeUpdater, processor: HomeProcessor, routeNavigator: RouteNavigator):
    BaseViewModel<HomeAction, HomeState, HomeSideEffect, HomeRenderState, HomeNavigation, GlobalEvent, ReduxGeneric.Error>(
        updater = updater,
        initialState = HomeState(),
        processor = processor,
        stateMapper = HomeStateMapper,
        routeNavigator = routeNavigator
    )
