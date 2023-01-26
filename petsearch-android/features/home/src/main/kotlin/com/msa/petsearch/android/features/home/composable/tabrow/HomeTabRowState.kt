package com.msa.petsearch.android.features.home.composable.tabrow

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy

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
        val Saver: Saver<HomeTabRowState, *> = Saver(
            save = { it.selectedTabIndex },
            restore = ::HomeTabRowState
        )
    }
}
