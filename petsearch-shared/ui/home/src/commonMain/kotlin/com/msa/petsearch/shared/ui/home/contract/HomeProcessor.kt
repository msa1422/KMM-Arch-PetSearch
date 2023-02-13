package com.msa.petsearch.shared.ui.home.contract

import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingConfig
import com.kuuurt.paging.multiplatform.PagingData
import com.kuuurt.paging.multiplatform.helpers.cachedIn
import com.msa.petsearch.shared.core.entity.PetSearchParams
import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.core.util.commonflow.CommonFlow
import com.msa.petsearch.shared.core.util.commonflow.asCommonFlow
import com.msa.petsearch.shared.core.util.extension.loadNextPage
import com.msa.petsearch.shared.core.util.resource.MessageType
import com.msa.petsearch.shared.core.util.resource.ResourceMessage
import com.msa.petsearch.shared.core.util.resource.Status
import com.msa.petsearch.shared.core.util.sharedviewmodel.store.Processor
import com.msa.petsearch.shared.domain.home.usecase.LoadPetsUseCase
import com.msa.petsearch.shared.ui.home.contract.store.Error
import com.msa.petsearch.shared.ui.home.contract.store.ForwardInitialDataToState
import com.msa.petsearch.shared.ui.home.contract.store.ForwardPetResponseToState
import com.msa.petsearch.shared.ui.home.contract.store.GetInitialData
import com.msa.petsearch.shared.ui.home.contract.store.HomeAction
import com.msa.petsearch.shared.ui.home.contract.store.HomeSideEffect
import com.msa.petsearch.shared.ui.home.contract.store.IdleAction
import com.msa.petsearch.shared.ui.home.contract.store.LoadPetListNextPage
import com.msa.petsearch.shared.ui.home.contract.store.OnPetTypeTabChanged
import com.msa.petsearch.shared.ui.home.model.HomeUseCaseWrapper
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.distinctUntilChanged

internal class HomeProcessor(
    private val useCases: HomeUseCaseWrapper,
    private val coroutineDispatcher: MainCoroutineDispatcher,
) : Processor<HomeSideEffect, HomeAction> {

    private lateinit var petListPager: Pager<Int, PetInfo>
    private lateinit var currentPetType: String
    private lateinit var coroutineScope: CoroutineScope

    private val pagingConfig = PagingConfig(
        pageSize = 20,
        prefetchDistance = 8,
        enablePlaceholders = false,
        initialLoadSize = 30
    )

    override suspend fun dispatchSideEffect(effect: HomeSideEffect) = when (effect) {
        is GetInitialData -> getInitialData()
        is OnPetTypeTabChanged -> onPetTypeTabChanged(effect)
        is LoadPetListNextPage -> loadPetListNextPage()
    }

    private suspend fun getInitialData(): HomeAction = useCases.getPetTypes().run {
        val firstType = data?.types?.firstOrNull()?.name

        return@run if (status == Status.SUCCESS && !firstType.isNullOrBlank()) {
            ForwardInitialDataToState(
                petTypes = data,
                petPagingData = getPetListFlow(type = firstType, params = PetSearchParams())
            )
        } else {
            onError(throwable ?: Throwable("No Items found in PetTypes list"))
        }
    }

    private suspend fun onPetTypeTabChanged(effect: OnPetTypeTabChanged) =
        ForwardPetResponseToState(getPetListFlow(type = effect.tabName, params = PetSearchParams()))

    private suspend fun getPetListFlow(
        type: String, params: PetSearchParams
    ): CommonFlow<PagingData<PetInfo>> {
        if (!::petListPager.isInitialized ||
            !::currentPetType.isInitialized ||
            currentPetType != type
        ) {
            if (!::coroutineScope.isInitialized) {
                val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                    onError(throwable)
                }

                coroutineScope =
                    CoroutineScope(SupervisorJob() + coroutineDispatcher + exceptionHandler)
            }

            petListPager = Pager(
                clientScope = coroutineScope,
                config = pagingConfig,
                initialKey = 1
            ) { currentKey, _ ->
                useCases.getPets(LoadPetsUseCase.Params(type, currentKey, params))
            }
        }

        return petListPager
            .pagingData
            .distinctUntilChanged()
            .cachedIn(coroutineScope)
            .asCommonFlow()
    }

    private fun loadPetListNextPage() = IdleAction.also { petListPager.loadNextPage() }

    private fun onError(throwable: Throwable?) =
        Error(ResourceMessage(text = throwable?.message, messageType = MessageType.SnackBar()))

    override fun close() {
        if (::coroutineScope.isInitialized) {
            coroutineScope.cancel()
        }
    }
}
