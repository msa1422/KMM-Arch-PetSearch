package com.msa.petsearch.shared.repository.home

import com.msa.petsearch.shared.coreentity.PetSearchParams
import com.msa.petsearch.shared.datainfrastructurenetwork.apiservice.ApiServiceDelegate
import com.msa.petsearch.shared.domain.homedatasource.HomeDataSource

internal class HomeDataSourceImpl(
    private val delegate: ApiServiceDelegate
) : HomeDataSource {

    override suspend fun getPetTypes() = delegate.getPetTypes()

    override suspend fun searchPets(type: String, page: Int, searchParams: PetSearchParams?) =
        delegate.searchPets(type, page, searchParams)

}
