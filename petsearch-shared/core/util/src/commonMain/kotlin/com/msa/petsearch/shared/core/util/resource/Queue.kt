package com.msa.petsearch.shared.core.util.resource

interface Queue<T : Any> {

    val count: Int

    val isEmpty: Boolean get() = count == 0

    fun enqueue(element: T): Boolean

    fun dequeue(): T?

    fun peekAndUpdate()
}
