@file:Suppress("DSL_SCOPE_VIOLATION")

import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    commonMainImplementation(libs.squareup.okio)
    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.uuid.multiplatform)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.kotlinx.serialization)
    commonMainImplementation(libs.kuuuurt.multiplatform.paging)

    commonMainImplementation(libs.kermit.log)
    //commonMainImplementation(libs.kermit.crashlytics)

    androidMainImplementation(libs.androidx.lifecycle.viewmodel.compose)
}
