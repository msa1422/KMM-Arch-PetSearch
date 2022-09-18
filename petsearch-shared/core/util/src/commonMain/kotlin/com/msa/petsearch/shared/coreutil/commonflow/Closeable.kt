package com.msa.petsearch.shared.coreutil.commonflow

// Remove when Kotlin's Closeable is supported in K/N https://youtrack.jetbrains.com/issue/KT-31066
// Alternatively use Ktor Closeable which is K/N ready.
// To avoid the dependency of Ktor, this interface is being used
interface Closeable {
    fun close()
}
