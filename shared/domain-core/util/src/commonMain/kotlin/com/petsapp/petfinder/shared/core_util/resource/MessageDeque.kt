package com.petsapp.petfinder.shared.core_util.resource

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class MessageDeque(private val deque: ArrayDeque<ResourceMessage>) :
    Queue<ResourceMessage> {

    private val message = MutableStateFlow(deque.firstOrNull())

    fun readOnlyStateFlow() = message.asStateFlow()

    override val count get() = deque.size

    override fun enqueue(element: ResourceMessage): Boolean {
        // prevent duplicate errors added to stack
        deque.firstOrNull { it.id == element.id }?.let {
            return false
        }

        val transaction = deque.add(element)

        peekAndUpdate()

        return transaction
    }

    override fun dequeue(): ResourceMessage? {
        // First remove the message from the ArrayDeque
        val transaction = deque.removeFirstOrNull()

        // Now remove the value from the FlowData
        message.value = null

        // Now peek and see if there are other messages in queue. If yes, then update FlowData
        peekAndUpdate()

        // Finally return the transaction data
        return transaction
    }

    override fun peekAndUpdate() {
        if (message.value == null) {
            deque.firstOrNull()?.let { message.value = it }
        }
    }
}

internal val MessageDequeParameter = ArrayDeque<ResourceMessage>()
