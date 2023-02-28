package com.msa.petsearch.shared.core.util.resource

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object MessageDeque : Queue<ResourceMessage> {
    private val deque = ArrayDeque<ResourceMessage>()
    private val message = MutableSharedFlow<ResourceMessage>()

    operator fun invoke() = message.asSharedFlow()

    override val count: Int
        get() = deque.size

    override suspend fun enqueue(element: ResourceMessage): Boolean {
        // If deque contains the element id return false,
        // No need to take any action in this case
        if (deque.any { it.id == element.id }) {
            return false
        }

        // Add the element to the deque
        val transaction = deque.add(element)

        // If the element being enqueued is the only element in Deque, emit the message
        // else wait for the Deque to be dequeued
        if (count == 1) {
            peekAndUpdate()
        }

        return transaction
    }

    override suspend fun dequeue(): ResourceMessage? {
        // First remove the message from the ArrayDeque
        val transaction = deque.removeFirstOrNull()

        // Now peek and see if there are other messages in queue. If yes, then update FlowData
        peekAndUpdate()

        // Finally return the transaction data
        return transaction
    }

    override suspend fun peekAndUpdate() {
        deque.firstOrNull()?.let { message.emit(it) }
    }
}
