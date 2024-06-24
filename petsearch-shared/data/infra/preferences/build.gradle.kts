@file:Suppress("UnstableApiUsage")

import com.msa.petsearch.SHARED_PACKAGE
import com.msa.petsearch.join
import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
}

android {
    namespace = SHARED_PACKAGE.join(
        projects.petsearchShared.data,
        projects.petsearchShared.data.infra,
        projects.petsearchShared.data.infra.preferences
    )
}

dependencies {
    commonMainImplementation(platform(libs.koin.bom))
    commonMainImplementation(libs.kermit.log)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.koin.core.coroutines)
    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.multiplatformSettings.coroutines)
    commonMainImplementation(libs.multiplatformSettings.noArg)
    commonMainImplementation(projects.petsearchShared.core.util)
}
