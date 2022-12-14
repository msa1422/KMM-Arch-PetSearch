package com.msa.petsearch.shared.domain.home.usecase

import com.kuuurt.paging.multiplatform.PagingResult
import com.msa.petsearch.shared.core.entity.PetSearchParams
import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.data.repository.AnimalRepository

class LoadPetsUseCase(private val repository: AnimalRepository) {

    data class Params(val type: String, val page: Int, val searchParams: PetSearchParams?)

    suspend operator fun invoke(arg: Params): PagingResult<Int, PetInfo> {
        val resource = repository.searchPets(arg.type, arg.page, arg.searchParams)

        return PagingResult(
            items = resource.data?.animals!!,
            currentKey = arg.page,
            prevKey = { null },
            nextKey = {
                val totalPageCount = resource.data?.pagination?.totalCount ?: 1
                arg.page.takeIf { it < totalPageCount }?.plus(1)
            }
        )
    }
}
