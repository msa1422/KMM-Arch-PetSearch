package com.petsapp.petfinder.shared.domain.homeuicontract.interactor

import com.petsapp.petfinder.shared.coreentity.response.PetTypesResponse
import com.petsapp.petfinder.shared.coreutil.FlowInteractor
import com.petsapp.petfinder.shared.coreutil.resource.Resource
import com.petsapp.petfinder.shared.domain.homedatasource.HomeDataSource
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
