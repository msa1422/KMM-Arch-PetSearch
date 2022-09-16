package com.msa.petsearch.shared.coreutil.sharedviewmodel.util

import kotlinx.coroutines.CoroutineScope

expect open class SuperViewModel constructor() {
    open val viewModelScope: CoroutineScope
    open fun onDestroy()
}
