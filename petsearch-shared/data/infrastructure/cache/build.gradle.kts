@file:Suppress("DSL_SCOPE_VIOLATION")

import com.msa.petsearch.PackageNameAccessor.DATA_INFRASTRUCTURE_CACHE
import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.realm.kotlin)
}

android {
    namespace = DATA_INFRASTRUCTURE_CACHE
}

dependencies {
    commonMainImplementation(projects.petsearchShared.core.util)

    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.realm.kotlin)
}
