package com.msa.petsearch.shared.core.util.resource

internal interface Queue<T : Any> {

    val count: Int

    val isEmpty: Boolean get() = count == 0

    suspend fun enqueue(element: T): Boolean

    suspend fun dequeue(): T?

    suspend fun peekAndUpdate()
}
