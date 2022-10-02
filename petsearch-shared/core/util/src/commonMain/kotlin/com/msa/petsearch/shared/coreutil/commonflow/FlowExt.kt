package com.msa.petsearch.shared.coreutil.commonflow

import kotlinx.coroutines.flow.Flow

fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)
