package com.msa.petsearch.shared.domain.homeuicontract.interactor

import com.kuuurt.paging.multiplatform.PagingResult
import com.msa.petsearch.shared.coreentity.PetSearchParams
import com.msa.petsearch.shared.coreentity.petinfo.PetInfo
import com.msa.petsearch.shared.coreutil.UseCase
import com.msa.petsearch.shared.domain.homedatasource.HomeDataSource

internal class LoadPetsUseCase(
    private val dataSource: HomeDataSource
) : UseCase<LoadPetsUseCase.Params, PagingResult<Int, PetInfo>>() {

    data class Params(val type: String, val page: Int, val searchParams: PetSearchParams?)

    override suspend fun run(
        arg: Params,
        onError: ((Throwable?) -> PagingResult<Int, PetInfo>)?
    ): PagingResult<Int, PetInfo> {
        val resource = dataSource.searchPets(arg.type, arg.page, arg.searchParams)

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
