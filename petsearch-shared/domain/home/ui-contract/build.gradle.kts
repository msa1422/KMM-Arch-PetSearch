@file:Suppress("UnstableApiUsage")

import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
}

dependencies {
    androidMainImplementation(libs.androidx.lifecycle.viewmodel.compose)

    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.kuuuurt.multiplatform.paging)
    commonMainImplementation(libs.kermit.log)

    commonMainImplementation(projects.petsearchShared.core.entity)
    commonMainImplementation(projects.petsearchShared.core.util)

    commonMainImplementation(projects.petsearchShared.domain.home.dataSource)

    commonTestImplementation(projects.petsearchShared.core.test)
    commonTestImplementation(kotlin("test"))
    commonTestImplementation(libs.testing.kotest.assertions)
    commonTestImplementation(libs.testing.turbine)
    commonTestImplementation(libs.kotlinx.coroutines.test)
}
