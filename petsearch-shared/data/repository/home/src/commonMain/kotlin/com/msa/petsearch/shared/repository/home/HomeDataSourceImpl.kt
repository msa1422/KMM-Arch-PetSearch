package com.msa.petsearch.shared.repository.home

import com.petsapp.petfinder.shared.coreentity.PetSearchParams
import com.petsapp.petfinder.shared.datainfrastructurenetwork.apiservice.ApiServiceDelegate
import com.petsapp.petfinder.shared.domain.homedatasource.HomeDataSource

internal class HomeDataSourceImpl(
    private val delegate: ApiServiceDelegate
) : HomeDataSource {

    override suspend fun getPetTypes() = delegate.getPetTypes()

    override suspend fun searchPets(type: String, page: Int, searchParams: PetSearchParams?) =
        delegate.searchPets(type, page, searchParams)

}
