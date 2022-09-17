@file:Suppress("UnstableApiUsage")

import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
    kotlin("plugin.serialization")
}

dependencies {
    commonMainImplementation(projects.petsearchShared.domainCore.entity)
    commonMainImplementation(projects.petsearchShared.domainCore.util)

    commonMainImplementation(projects.petsearchShared.domain.home.dataSource)

    commonMainImplementation(projects.petsearchShared.data.infrastructure.network)
    commonMainImplementation(projects.petsearchShared.data.infrastructure.cache)

    commonMainImplementation(libs.kotlinx.serialization)
    commonMainImplementation(libs.koin.core)
}
