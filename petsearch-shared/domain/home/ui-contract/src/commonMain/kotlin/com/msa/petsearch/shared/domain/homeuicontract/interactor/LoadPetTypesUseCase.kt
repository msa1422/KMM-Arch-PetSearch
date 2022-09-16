package com.msa.petsearch.shared.domain.homeuicontract.interactor

import com.msa.petsearch.shared.coreentity.response.PetTypesResponse
import com.msa.petsearch.shared.coreutil.FlowInteractor
import com.msa.petsearch.shared.coreutil.resource.Resource
import com.msa.petsearch.shared.domain.homedatasource.HomeDataSource
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
