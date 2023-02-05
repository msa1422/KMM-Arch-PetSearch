package com.msa.petsearch.shared.ui.home.contract.store

import com.msa.petsearch.shared.core.util.sharedviewmodel.store.NanoRedux

sealed interface HomeSideEffect : NanoRedux.SideEffect

object GetInitialData : HomeSideEffect
