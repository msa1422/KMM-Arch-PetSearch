package com.msa.petsearch.shared.ui.home.contract.store

import com.msa.petsearch.shared.core.entity.PetSearchParams
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux

sealed interface HomeSideEffect : NanoRedux.SideEffect {

    object LoadPetTypesFromNetwork : HomeSideEffect

    data class LoadPetsFromNetwork(
        val type: String,
        val page: Int,
        val params: PetSearchParams
    ) : HomeSideEffect

    object LoadPetListNextPageFromNetwork : HomeSideEffect
}
