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
    commonMainImplementation(projects.petsearchShared.core.util)

    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.multiplatformSettings.noArg)
    commonMainImplementation(libs.multiplatformSettings.coroutines)
    commonMainImplementation(libs.kermit.log)
}
