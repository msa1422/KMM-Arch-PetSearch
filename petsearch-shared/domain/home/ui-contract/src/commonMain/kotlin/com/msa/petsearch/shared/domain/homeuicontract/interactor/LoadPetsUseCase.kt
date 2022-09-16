package com.msa.petsearch.shared.domain.homeuicontract.interactor

import com.petsapp.petfinder.shared.coreentity.PetSearchParams
import com.petsapp.petfinder.shared.coreentity.response.SearchPetResponse
import com.petsapp.petfinder.shared.coreutil.FlowInteractor
import com.petsapp.petfinder.shared.coreutil.resource.Resource
import com.petsapp.petfinder.shared.domain.homedatasource.HomeDataSource
import kotlinx.coroutines.flow.*

class LoadPetsUseCase(
    private val dataSource: HomeDataSource
) : FlowInteractor<LoadPetsUseCase.Params, Resource<SearchPetResponse?>>() {

    data class Params(
        val type: String,
        val page: Int,
        val searchParams: PetSearchParams?
    )

    override fun run(params: Params?): Flow<Resource<SearchPetResponse?>> {
        return flow {
            params
                ?.let {
                    emit(dataSource.searchPets(it.type, it.page, it.searchParams))
                }
                ?: kotlin.run {
                    throw IllegalArgumentException("Not enough parameters to fetch the data.")
                }
        }
            .catch { emit(Resource.error(it, null)) }
    }

}
