@file:Suppress("DSL_SCOPE_VIOLATION")

import com.msa.petsearch.SHARED_PACKAGE
import com.msa.petsearch.join
import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kmp.nativecoroutines)
}

android {
    namespace = SHARED_PACKAGE.join(
        projects.petsearchShared.core,
        projects.petsearchShared.core.util
    )
}

dependencies {
    androidMainImplementation(libs.androidx.lifecycle.viewmodel.compose)


    commonMainImplementation(platform(libs.koin.bom))
    commonMainImplementation(libs.kermit.log)
    commonMainImplementation(libs.kmp.observableviewmodel)
    //commonMainImplementation(libs.kermit.crashlytics)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.koin.core.coroutines)
    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.kotlinx.serialization)
    commonMainImplementation(libs.kuuuurt.multiplatform.paging)
    commonMainImplementation(libs.squareup.okio)
    commonMainImplementation(libs.touchlab.skie.annotations)
    commonMainImplementation(libs.uuid.multiplatform)
}
