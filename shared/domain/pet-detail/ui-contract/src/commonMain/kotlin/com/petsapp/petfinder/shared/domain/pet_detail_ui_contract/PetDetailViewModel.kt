package com.petsapp.petfinder.shared.domain.pet_detail_ui_contract

import com.petsapp.petfinder.shared.core_util.shared_viewmodel.BaseViewModel
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.navigation.RouteNavigator
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.util.GlobalEvent
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.util.ReduxGeneric
import com.petsapp.petfinder.shared.domain.pet_detail_ui_contract.contract.mapper.PetDetailArgsMapper
import com.petsapp.petfinder.shared.domain.pet_detail_ui_contract.contract.store.PetDetailState

class PetDetailViewModel(routeNavigator: RouteNavigator):
    BaseViewModel<Nothing, PetDetailState, Nothing, Nothing, Nothing, GlobalEvent, ReduxGeneric.Error>(
        initialState = PetDetailState(),
        argsMapper = PetDetailArgsMapper,
        routeNavigator = routeNavigator
    )