package com.msa.petsearch.shared.coreutil.sharedviewmodel.util

import androidx.lifecycle.ViewModel

actual abstract class SuperViewModel actual constructor() : ViewModel() {

    // Not required in this architecture
    // protected actual val viewModelScope: CoroutineScope = androidViewModelScope

    actual override fun onCleared() {
        super.onCleared()
    }
}
