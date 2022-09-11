package com.petsapp.petfinder.shared.repository.home

import com.petsapp.petfinder.shared.core_entity.PetSearchParams
import com.petsapp.petfinder.shared.data_infrastructure_network.api_service.ApiServiceDelegate
import com.petsapp.petfinder.shared.domain.home_data_source.HomeDataSource

internal class HomeDataSourceImpl(
    private val delegate: ApiServiceDelegate
) : HomeDataSource {

    override suspend fun getPetTypes() = delegate.getPetTypes()

    override suspend fun searchPets(type: String, page: Int, searchParams: PetSearchParams?) =
        delegate.searchPets(type, page, searchParams)

}
