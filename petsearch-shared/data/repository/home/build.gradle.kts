@file:Suppress("DSL_SCOPE_VIOLATION")

import com.msa.petsearch.PackageNameAccessor.REPOSITORY_HOME
import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = REPOSITORY_HOME
}

dependencies {
    commonMainImplementation(projects.petsearchShared.core.entity)
    commonMainImplementation(projects.petsearchShared.core.util)

    commonMainImplementation(projects.petsearchShared.domain.home.dataSource)

    commonMainImplementation(projects.petsearchShared.data.infrastructure.network)
    commonMainImplementation(projects.petsearchShared.data.infrastructure.cache)

    commonMainImplementation(libs.kotlinx.serialization)
    commonMainImplementation(libs.koin.core)
}
