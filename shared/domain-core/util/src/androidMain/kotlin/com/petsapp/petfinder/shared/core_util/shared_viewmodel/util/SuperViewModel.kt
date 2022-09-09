package com.petsapp.petfinder.shared.core_util.shared_viewmodel.util

import androidx.lifecycle.ViewModel
import com.petsapp.petfinder.shared.core_util.shared_viewmodel.coroutines.createViewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

actual open class SuperViewModel actual constructor(): ViewModel() {
    actual open val viewModelScope: CoroutineScope = createViewModelScope()
    actual open fun onDestroy() {
        viewModelScope.cancel()
    }
    override fun onCleared() {
        onDestroy()
        super.onCleared()
    }
}