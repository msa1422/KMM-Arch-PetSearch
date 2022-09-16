package com.msa.petsearch.shared.domain.homeuicontract.contract.store

import com.petsapp.petfinder.shared.coreentity.PetSearchParams
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.store.NanoRedux

sealed class HomeSideEffect : NanoRedux.SideEffect {

    object LoadPetTypesFromNetwork : HomeSideEffect()

    data class LoadPetsFromNetwork(
        val type: String,
        val page: Int,
        val params: PetSearchParams
    ) : HomeSideEffect()

    object LoadPetListNextPageFromNetwork: HomeSideEffect()

}
