package com.petsapp.petfinder.shared.coreutil.sharedviewmodel.util

import androidx.lifecycle.ViewModel
import com.petsapp.petfinder.shared.coreutil.sharedviewmodel.coroutines.createViewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

actual open class SuperViewModel actual constructor() : ViewModel() {

    actual open val viewModelScope: CoroutineScope = createViewModelScope()

    actual open fun onDestroy() {
        viewModelScope.cancel()
    }

    override fun onCleared() {
        onDestroy()
        super.onCleared()
    }
}
