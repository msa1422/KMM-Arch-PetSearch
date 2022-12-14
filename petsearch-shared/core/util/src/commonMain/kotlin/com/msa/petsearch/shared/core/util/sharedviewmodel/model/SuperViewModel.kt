package com.msa.petsearch.shared.core.util.sharedviewmodel.model

import kotlinx.coroutines.CoroutineScope

expect open class SuperViewModel constructor() {
    protected val viewModelScope: CoroutineScope
    protected open fun onCleared()
}
