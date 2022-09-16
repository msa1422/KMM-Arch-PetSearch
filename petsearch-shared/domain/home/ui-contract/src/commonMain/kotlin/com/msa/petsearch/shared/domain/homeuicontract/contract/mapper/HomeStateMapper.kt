package com.msa.petsearch.shared.domain.homeuicontract.contract.mapper

import com.msa.petsearch.shared.coreutil.sharedviewmodel.store.StateMapper
import com.msa.petsearch.shared.domain.homeuicontract.contract.store.HomeRenderState
import com.msa.petsearch.shared.domain.homeuicontract.contract.store.HomeState

object HomeStateMapper : StateMapper<HomeState, HomeRenderState> {
    override fun mapToRenderState(state: HomeState) =
        HomeRenderState(
            petTypes = state.petTypesResponse?.types,
            petPagingData = state.petPagingData
        )
}
