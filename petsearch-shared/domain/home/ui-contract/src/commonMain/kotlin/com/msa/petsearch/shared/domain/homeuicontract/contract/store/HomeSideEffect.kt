package com.msa.petsearch.shared.domain.homeuicontract.contract.store

import com.msa.petsearch.shared.coreentity.PetSearchParams
import com.msa.petsearch.shared.coreutil.sharedviewmodel.store.NanoRedux
import kotlinx.coroutines.CoroutineScope

sealed interface HomeSideEffect : NanoRedux.SideEffect {

    object LoadPetTypesFromNetwork : HomeSideEffect

    data class LoadPetsFromNetwork(
        val type: String,
        val page: Int,
        val params: PetSearchParams
    ) : HomeSideEffect

    object LoadPetListNextPageFromNetwork: HomeSideEffect
}
