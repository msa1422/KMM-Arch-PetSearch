package com.petsapp.petfinder.shared.coreutil.sharedviewmodel.util

import kotlinx.coroutines.CoroutineScope

expect open class SuperViewModel constructor() {
    open val viewModelScope: CoroutineScope
    open fun onDestroy()
}
