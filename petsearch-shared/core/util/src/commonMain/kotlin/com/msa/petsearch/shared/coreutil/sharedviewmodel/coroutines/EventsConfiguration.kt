package com.msa.petsearch.shared.coreutil.sharedviewmodel.coroutines

import kotlinx.coroutines.channels.BufferOverflow

data class EventsConfiguration(
    val replays: Int = 10,
    val extraBufferCapacity: Int = 10,
    val backPressureStrategy: BufferOverflow = BufferOverflow.DROP_LATEST
)
