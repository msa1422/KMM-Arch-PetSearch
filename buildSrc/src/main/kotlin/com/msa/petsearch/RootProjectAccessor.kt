package com.msa.petsearch
import org.gradle.api.internal.catalog.DelegatingProjectDependency

typealias RootProjectAccessor = String

fun RootProjectAccessor.join(vararg other: DelegatingProjectDependency): RootProjectAccessor {
    var accessor = this
    other.forEach {
        accessor = accessor.plus(".${it.name}")
    }
    return accessor
}

private const val ROOT_PACKAGE: RootProjectAccessor = "com.msa.petsearch"

const val SHARED_PACKAGE: RootProjectAccessor = "$ROOT_PACKAGE.shared"
const val ANDROID_PACKAGE: RootProjectAccessor = "$ROOT_PACKAGE.android"
