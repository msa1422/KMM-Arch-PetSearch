package com.msa.petsearch.shared.ui.home

import com.msa.petsearch.shared.core.util.sharedviewmodel.BaseViewModel
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.RouteNavigator
import com.msa.petsearch.shared.ui.home.contract.HomeProcessor
import com.msa.petsearch.shared.ui.home.contract.HomeUpdater
import com.msa.petsearch.shared.ui.home.contract.store.GetInitialData
import com.msa.petsearch.shared.core.util.sharedviewmodel.model.GlobalEvent as GE
import com.msa.petsearch.shared.ui.home.contract.mapper.HomeStateMapper as HSM
import com.msa.petsearch.shared.ui.home.contract.store.HomeAction as HA
import com.msa.petsearch.shared.ui.home.contract.store.HomeNavigation as HN
import com.msa.petsearch.shared.ui.home.contract.store.HomeRenderState as HRS
import com.msa.petsearch.shared.ui.home.contract.store.HomeSideEffect as HSE
import com.msa.petsearch.shared.ui.home.contract.store.HomeState as HS

class HomeViewModel
internal constructor(
    updater: HomeUpdater,
    processor: HomeProcessor,
    routeNavigator: RouteNavigator
) : BaseViewModel<HA, HS, HSE, HRS, HN, GE>(
    updater = updater,
    initialState = HS(),
    processor = processor,
    stateMapper = HSM,
    routeNavigator = routeNavigator,
    initialEffects = setOf(GetInitialData)
)
