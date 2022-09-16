package com.msa.petsearch.shared.domain.petdetailuicontract

import com.msa.petsearch.shared.coreutil.sharedviewmodel.BaseViewModel
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.RouteNavigator
import com.msa.petsearch.shared.coreutil.sharedviewmodel.util.GlobalEvent
import com.msa.petsearch.shared.coreutil.sharedviewmodel.util.ReduxGeneric
import com.msa.petsearch.shared.domain.petdetailuicontract.contract.mapper.PetDetailArgsMapper
import com.msa.petsearch.shared.domain.petdetailuicontract.contract.store.PetDetailState

class PetDetailViewModel(routeNavigator: RouteNavigator):
    BaseViewModel<Nothing, PetDetailState, Nothing, Nothing, Nothing, GlobalEvent, ReduxGeneric.Error>(
        initialState = PetDetailState(),
        argsMapper = PetDetailArgsMapper,
        routeNavigator = routeNavigator
    )
