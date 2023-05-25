package com.msa.petsearch.shared.ui.home

import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.helpers.cachedIn
import com.msa.petsearch.shared.core.entity.PetSearchParams
import com.msa.petsearch.shared.core.entity.PetType
import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.core.util.extension.loadNextPage
import com.msa.petsearch.shared.core.util.kmmviewmodel.BaseKmmViewModel
import com.msa.petsearch.shared.core.util.resource.MessageType.SnackBar
import com.msa.petsearch.shared.core.util.resource.ResourceMessage
import com.msa.petsearch.shared.core.util.resource.Status
import com.msa.petsearch.shared.domain.home.usecase.LoadPetsUseCase
import com.msa.petsearch.shared.ui.home.contract.HomeAction
import com.msa.petsearch.shared.ui.home.contract.HomeNavigation
import com.msa.petsearch.shared.ui.home.contract.LoadPetListNextPage
import com.msa.petsearch.shared.ui.home.contract.NavigateToPetDetail
import com.msa.petsearch.shared.ui.home.contract.OnPetTypeTabChanged
import com.msa.petsearch.shared.ui.home.model.PagerConfig
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel
internal constructor(private val useCases: HomeUseCaseWrapper) :
    BaseKmmViewModel<HomeAction, HomeNavigation, Nothing>() {

    private val _petTypes = MutableStateFlow(emptyList<PetType>())

    private val _currentPetType = MutableStateFlow("")

    private val _pager = _currentPetType
        .filterNot(::isCurrentTypeBlank)
        .mapLatest(::createPager)
        .stateInWhenSubscribed(initialValue = null)

    @NativeCoroutinesState
    val petTypes = _petTypes.stateInWhenSubscribed(initialValue = emptyList())

    @NativeCoroutinesState
    val pagingData = _pager
        .filterNotNull()
        .flatMapLatest(Pager<Int, PetInfo>::pagingData::invoke)
        .distinctUntilChanged()
        .cachedIn(vScope)

    override fun dispatch(action: HomeAction) = when (action) {
        is NavigateToPetDetail -> emit(action)
        is OnPetTypeTabChanged -> _currentPetType.update { action.tabName }
        is LoadPetListNextPage -> vScope.launch { _pager.value?.loadNextPage() }
            .run { } // Hack to provide expected return type
    }

    private fun createPager(type: String) =
        Pager(clientScope = vScope, config = PagerConfig, initialKey = 1) { key, _ ->
            useCases.getPets(LoadPetsUseCase.Params(type, key, PetSearchParams()))
        }

    private suspend fun isCurrentTypeBlank(type: String): Boolean {
        if (type.isBlank()) {
            useCases.getPetTypes().run {
                val firstType = data?.types?.firstOrNull()?.name

                if (status == Status.SUCCESS && !firstType.isNullOrBlank()) {
                    _currentPetType.update { firstType }
                    _petTypes.update { data?.types!! }
                } else {
                    val message = ResourceMessage.Builder()
                        .text(throwable?.message)
                        .messageType(SnackBar.Builder().build())
                        .build()

                    emit(message)
                }
            }
        }

        return type.isBlank()
    }
}
