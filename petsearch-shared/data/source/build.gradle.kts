@file:Suppress("DSL_SCOPE_VIOLATION")

import com.msa.petsearch.SHARED_PACKAGE
import com.msa.petsearch.join
import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = SHARED_PACKAGE.join(
        projects.petsearchShared.data,
        projects.petsearchShared.data.source
    )
}

dependencies {
    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.bundles.ktor.common)

    commonMainImplementation(projects.petsearchShared.core.entity)
    commonMainImplementation(projects.petsearchShared.core.util)
    commonMainImplementation(projects.petsearchShared.data.repository)
    commonMainImplementation(projects.petsearchShared.data.infra.network)

    commonTestImplementation(libs.ktor.client.mock)
}
