package com.petsapp.petfinder.shared.domain.home_ui_contract.contract.mapper

import com.petsapp.petfinder.shared.core_util.shared_viewmodel.store.StateMapper
import com.petsapp.petfinder.shared.domain.home_ui_contract.contract.store.HomeRenderState
import com.petsapp.petfinder.shared.domain.home_ui_contract.contract.store.HomeState

object HomeStateMapper : StateMapper<HomeState, HomeRenderState> {
    override fun mapToRenderState(state: HomeState) =
        HomeRenderState(
            petTypes = state.petTypesResponse?.types,
            petPagingData = state.petPagingData
        )
}
