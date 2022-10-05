@file:Suppress("UnstableApiUsage")

import com.msa.petsearch.PackageNameAccessor.DATA_INFRASTRUCTURE_PREFS
import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
}

android {
    namespace = DATA_INFRASTRUCTURE_PREFS
}

dependencies {
    commonMainImplementation(projects.petsearchShared.core.util)

    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.multiplatformSettings.noArg)
    commonMainImplementation(libs.multiplatformSettings.coroutines)
    commonMainImplementation(libs.kermit.log)
}
