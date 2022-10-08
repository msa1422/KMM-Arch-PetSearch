package com.msa.petsearch.shared.domain.homeuicontract.interactor

import com.msa.petsearch.shared.coreutil.UseCase
import com.msa.petsearch.shared.coreutil.resource.Status
import com.msa.petsearch.shared.domain.homedatasource.HomeDataSource
import com.msa.petsearch.shared.domain.homeuicontract.contract.store.HomeAction

internal class LoadPetTypesUseCase(
    private val dataSource: HomeDataSource
) : UseCase<Unit, HomeAction>() {

    override suspend fun run(arg: Unit, onError: ((Throwable?) -> HomeAction)?): HomeAction {
        val resource = dataSource.getPetTypes()

        return when (resource.status) {
            Status.SUCCESS -> HomeAction.UpdatePetTypesInState(resource.data)
            Status.ERROR -> onError!!(resource.throwable)
        }
    }
}
