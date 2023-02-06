package com.msa.petsearch.shared.ui.petdetail

import com.msa.petsearch.shared.core.util.sharedviewmodel.BaseViewModel
import com.msa.petsearch.shared.core.util.sharedviewmodel.model.GlobalEvent
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.RouteNavigator
import com.msa.petsearch.shared.ui.petdetail.contract.mapper.PetDetailArgsMapper
import com.msa.petsearch.shared.ui.petdetail.contract.store.PetDetailState

class PetDetailViewModel
internal constructor(routeNavigator: RouteNavigator) :
    BaseViewModel<Nothing, PetDetailState, Nothing, Nothing, Nothing, GlobalEvent>(
        initialState = PetDetailState(),
        argsMapper = PetDetailArgsMapper,
        routeNavigator = routeNavigator
    )
