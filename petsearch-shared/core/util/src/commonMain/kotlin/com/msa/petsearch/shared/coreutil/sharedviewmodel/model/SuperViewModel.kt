package com.msa.petsearch.shared.coreutil.sharedviewmodel.model

import kotlinx.coroutines.CoroutineScope

expect abstract class SuperViewModel constructor() {
    protected val viewModelScope: CoroutineScope
    protected open fun onCleared()
}
