package com.msa.petsearch.shared.ui.petdetail

import com.msa.petsearch.shared.core.util.extension.stateInWhenSubscribed
import com.msa.petsearch.shared.core.util.sharedviewmodel.BaseViewModel
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.RouteNavigator
import com.msa.petsearch.shared.ui.petdetail.contract.mapper.PetDetailArgsMapper
import com.msa.petsearch.shared.ui.petdetail.contract.store.PetDetailNavArgs
import kotlinx.coroutines.flow.map

class PetDetailViewModel
internal constructor(navigator: RouteNavigator) :
    BaseViewModel<Nothing, Nothing, Nothing, PetDetailNavArgs>(
        emptyArgs = PetDetailNavArgs(),
        argsMapper = PetDetailArgsMapper,
        routeNavigator = navigator
    )
{
    val petInfo = navArgs
        .map { it.petInfo }
        .stateInWhenSubscribed(scope = viewModelScope, initialValue = null)

    // No action to dispatch
    override fun dispatch(action: Nothing) = Unit
}
