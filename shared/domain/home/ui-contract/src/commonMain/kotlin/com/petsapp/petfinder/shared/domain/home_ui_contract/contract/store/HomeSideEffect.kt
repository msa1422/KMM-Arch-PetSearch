package com.petsapp.petfinder.shared.domain.home_ui_contract.contract.store

import com.petsapp.petfinder.shared.core_entity.PetSearchParams
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.store.NanoRedux

sealed class HomeSideEffect : NanoRedux.SideEffect {

    object LoadPetTypesFromNetwork : HomeSideEffect()

    data class LoadPetsFromNetwork(
        val type: String,
        val page: Int,
        val params: PetSearchParams
    ) : HomeSideEffect()

}
