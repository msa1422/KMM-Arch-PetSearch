package com.petsapp.petfinder.shared.domain.home_ui_contract.data_interactor

import com.petsapp.petfinder.shared.core_entity.response.PetTypesResponse
import com.petsapp.petfinder.shared.core_util.FlowInteractor
import com.petsapp.petfinder.shared.core_util.resource.Resource
import com.petsapp.petfinder.shared.domain.home_data_source.HomeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LoadPetTypesUseCase(
    private val dataSource: HomeDataSource
) : FlowInteractor<Nothing, Resource<PetTypesResponse?>>() {

    override fun run(params: Nothing?): Flow<Resource<PetTypesResponse?>> {
        return flow { emit(dataSource.getPetTypes()) }
            .catch { emit(Resource.error(it, null)) }
    }

}
