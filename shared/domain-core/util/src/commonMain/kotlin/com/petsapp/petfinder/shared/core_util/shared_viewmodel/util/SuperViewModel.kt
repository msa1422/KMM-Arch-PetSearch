package com.petsapp.petfinder.shared.core_util.shared_viewmodel.util

import kotlinx.coroutines.CoroutineScope

expect open class SuperViewModel constructor() {
    open val viewModelScope: CoroutineScope
    open fun onDestroy()
}