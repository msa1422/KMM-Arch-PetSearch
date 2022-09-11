package com.petsapp.petfinder.home.composable.homeTabRow

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
internal fun rememberHomeTabRowState(selectedTabIndex: Int = 0): HomeTabRowState {
    return rememberSaveable(saver = HomeTabRowState.Saver) {
        HomeTabRowState(selectedTabIndex)
    }
}

@Stable
class HomeTabRowState(selectedTabIndex: Int = 0) {

    private var _selectedTabIndex by mutableStateOf(selectedTabIndex, structuralEqualityPolicy())

    var selectedTabIndex: Int
        get() = _selectedTabIndex
        set(value) {
            require(value >= 0) { "Index value must be greater than or equal to 0" }
            _selectedTabIndex = value
        }

    companion object {
        /**
         * The default [Saver] implementation for [HomeTabRowState].
         */
        val Saver: Saver<HomeTabRowState, *> = listSaver(
            save = { listOf(it.selectedTabIndex) },
            restore = {
                HomeTabRowState(selectedTabIndex = it[0])
            }
        )
    }
}
