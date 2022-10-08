package com.msa.petsearch.shared.coreutil.sharedviewmodel.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import androidx.lifecycle.viewModelScope as androidViewModelScope

actual abstract class SuperViewModel actual constructor() : ViewModel() {

    protected actual val viewModelScope: CoroutineScope = androidViewModelScope

    actual override fun onCleared() {
        super.onCleared()
    }
}
