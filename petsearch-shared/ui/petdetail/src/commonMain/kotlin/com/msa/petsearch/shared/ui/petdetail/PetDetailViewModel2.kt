package com.msa.petsearch.shared.ui.petdetail

import com.msa.petsearch.shared.core.util.extension.stateInWhenSubscribed
import com.msa.petsearch.shared.core.util.sharedviewmodel.BaseViewModel2
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.RouteNavigator
import com.msa.petsearch.shared.ui.petdetail.contract.mapper.PetDetailArgsMapper2
import com.msa.petsearch.shared.ui.petdetail.contract.store.PetDetailState
import kotlinx.coroutines.flow.map

class PetDetailViewModel2
internal constructor(routeNavigator: RouteNavigator) :
    BaseViewModel2<Nothing, Nothing, Nothing, PetDetailState>(
        emptyArgs = PetDetailState(),
        argsMapper = PetDetailArgsMapper2,
        routeNavigator = routeNavigator
    )
{
    val petInfo = navArgs
        .map { it.petInfo }
        .stateInWhenSubscribed(scope = viewModelScope, initialValue = null)

    // No action to dispatch
    override fun dispatch(action: Nothing) = Unit
}