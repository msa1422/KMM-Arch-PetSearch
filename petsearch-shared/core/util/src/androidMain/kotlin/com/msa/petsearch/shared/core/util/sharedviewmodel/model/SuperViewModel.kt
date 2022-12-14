package com.msa.petsearch.shared.core.util.sharedviewmodel.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import androidx.lifecycle.viewModelScope as androidViewModelScope

actual open class SuperViewModel actual constructor() : ViewModel() {

    protected actual val viewModelScope: CoroutineScope = androidViewModelScope

    actual override fun onCleared() {
        super.onCleared()
    }
}
