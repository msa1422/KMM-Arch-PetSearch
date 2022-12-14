package com.msa.petsearch.shared.ui.home.contract.mapper

import com.msa.petsearch.shared.core.util.sharedviewmodel.store.StateMapper
import com.msa.petsearch.shared.ui.home.contract.store.HomeRenderState
import com.msa.petsearch.shared.ui.home.contract.store.HomeState

internal object HomeStateMapper : StateMapper<HomeState, HomeRenderState> {
    override fun mapToRenderState(state: HomeState) =
        HomeRenderState(
            petTypes = state.petTypesResponse?.types,
            petPagingData = state.petPagingData
        )
}
