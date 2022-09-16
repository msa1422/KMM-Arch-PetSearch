package com.petsapp.petfinder.shared.domain.petdetailuicontract

import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.BaseViewModel
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.navigation.RouteNavigator
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.util.GlobalEvent
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.util.ReduxGeneric
import com.petsapp.petfinder.shared.domain.petdetailuicontract.contract.mapper.PetDetailArgsMapper
import com.petsapp.petfinder.shared.domain.petdetailuicontract.contract.store.PetDetailState

class PetDetailViewModel(routeNavigator: RouteNavigator):
    BaseViewModel<Nothing, PetDetailState, Nothing, Nothing, Nothing, GlobalEvent, ReduxGeneric.Error>(
        initialState = PetDetailState(),
        argsMapper = PetDetailArgsMapper,
        routeNavigator = routeNavigator
    )
