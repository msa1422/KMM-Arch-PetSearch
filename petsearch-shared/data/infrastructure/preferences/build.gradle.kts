@file:Suppress("UnstableApiUsage")

import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
}

dependencies {
    commonMainImplementation(projects.petsearchShared.domainCore.util)

    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.multiplatformSettings.noArg)
    commonMainImplementation(libs.multiplatformSettings.coroutines)
    commonMainImplementation(libs.kermit.log)
}
