package com.msa.petsearch.shared.ui.home

import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.helpers.cachedIn
import com.msa.petsearch.shared.core.entity.PetSearchParams
import com.msa.petsearch.shared.core.entity.PetType
import com.msa.petsearch.shared.core.util.extension.loadNextPage
import com.msa.petsearch.shared.core.util.extension.stateInWhenSubscribed
import com.msa.petsearch.shared.core.util.resource.MessageType.SnackBar
import com.msa.petsearch.shared.core.util.resource.ResourceMessage
import com.msa.petsearch.shared.core.util.resource.Status
import com.msa.petsearch.shared.core.util.sharedviewmodel.BaseViewModel
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.RouteNavigator
import com.msa.petsearch.shared.domain.home.usecase.LoadPetsUseCase
import com.msa.petsearch.shared.ui.home.contract.HomeAction
import com.msa.petsearch.shared.ui.home.contract.HomeNavigation
import com.msa.petsearch.shared.ui.home.contract.LoadPetListNextPage
import com.msa.petsearch.shared.ui.home.contract.NavigateToPetDetail
import com.msa.petsearch.shared.ui.home.contract.OnPetTypeTabChanged
import com.msa.petsearch.shared.ui.home.model.PagerConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel
internal constructor(private val useCases: HomeUseCaseWrapper, routeNavigator: RouteNavigator) :
    BaseViewModel<HomeAction, HomeNavigation, Nothing, Nothing>(routeNavigator = routeNavigator) {

    private val _petTypes = MutableStateFlow(emptyList<PetType>())
    private val _currentPetType = MutableStateFlow("")
    private val _pager = _currentPetType
        .filterNot(::isCurrentTypeBlank)
        .mapLatest(::createPager)

    val petTypes = _petTypes
        .stateInWhenSubscribed(scope = viewModelScope, initialValue = emptyList())

    val pagingData = _pager
        .flatMapLatest { it.pagingData }
        .distinctUntilChanged()
        .cachedIn(viewModelScope)

    override fun dispatch(action: HomeAction) = when (action) {
        is NavigateToPetDetail -> emit(action)
        is OnPetTypeTabChanged -> _currentPetType.update { action.tabName }
        is LoadPetListNextPage -> viewModelScope.launch { _pager.first().loadNextPage() }
            .run { } // Hack to provide expected return type
    }

    private suspend fun isCurrentTypeBlank(type: String): Boolean {
        if (type.isBlank()) {
            useCases.getPetTypes().run {
                val firstType = data?.types?.firstOrNull()?.name

                if (status == Status.SUCCESS && !firstType.isNullOrBlank()) {
                    _currentPetType.update { firstType }
                    _petTypes.update { data?.types!! }
                } else {
                    emit(ResourceMessage(text = throwable?.message, messageType = SnackBar()))
                }
            }
        }

        return type.isBlank()
    }

    private fun createPager(type: String) =
        Pager(clientScope = viewModelScope, config = PagerConfig, initialKey = 1) { key, _ ->
            useCases.getPets(LoadPetsUseCase.Params(type, key, PetSearchParams()))
        }
}
