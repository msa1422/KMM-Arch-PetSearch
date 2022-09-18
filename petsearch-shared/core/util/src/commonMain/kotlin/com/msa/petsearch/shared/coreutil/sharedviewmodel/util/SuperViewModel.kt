package com.msa.petsearch.shared.coreutil.sharedviewmodel.util

expect abstract class SuperViewModel constructor() {
    // protected val viewModelScope: CoroutineScope
    protected open fun onCleared()
}
