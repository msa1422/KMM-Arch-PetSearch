package com.msa.petsearch.shared.domain.petdetailuicontract

import com.msa.petsearch.shared.coreutil.resource.MessageDeque
import com.msa.petsearch.shared.coreutil.sharedviewmodel.BaseViewModel
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.RouteNavigator
import com.msa.petsearch.shared.coreutil.sharedviewmodel.model.GlobalEvent
import com.msa.petsearch.shared.coreutil.sharedviewmodel.model.ReduxGeneric
import com.msa.petsearch.shared.domain.petdetailuicontract.contract.mapper.PetDetailArgsMapper
import com.msa.petsearch.shared.domain.petdetailuicontract.contract.store.PetDetailState

class PetDetailViewModel
internal constructor(routeNavigator: RouteNavigator, messageDeque: MessageDeque) :
    BaseViewModel<Nothing, PetDetailState, Nothing, Nothing, Nothing, GlobalEvent, ReduxGeneric.Error>(
        initialState = PetDetailState(),
        argsMapper = PetDetailArgsMapper,
        routeNavigator = routeNavigator,
        messageDeque = messageDeque
    )
