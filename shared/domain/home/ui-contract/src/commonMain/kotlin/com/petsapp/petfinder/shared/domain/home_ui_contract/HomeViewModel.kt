package com.petsapp.petfinder.shared.domain.home_ui_contract

import com.petsapp.petfinder.shared.core_util.shared_viewmodel.BaseViewModel
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.navigation.RouteNavigator
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.util.GlobalEvent
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.util.ReduxGeneric
import com.petsapp.petfinder.shared.domain.home_ui_contract.contract.HomeProcessor
import com.petsapp.petfinder.shared.domain.home_ui_contract.contract.HomeUpdater
import com.petsapp.petfinder.shared.domain.home_ui_contract.contract.mapper.HomeStateMapper
import com.petsapp.petfinder.shared.domain.home_ui_contract.contract.store.*

class HomeViewModel(updater: HomeUpdater, processor: HomeProcessor, routeNavigator: RouteNavigator):
    BaseViewModel<HomeAction, HomeState, HomeSideEffect, HomeRenderState, HomeNavigation, GlobalEvent, ReduxGeneric.Error>(
        updater = updater,
        initialState = HomeState(),
        processor = processor,
        stateMapper = HomeStateMapper,
        routeNavigator = routeNavigator
    )