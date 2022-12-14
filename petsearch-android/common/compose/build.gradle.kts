@file:Suppress("DSL_SCOPE_VIOLATION")

import com.msa.petsearch.ANDROID_PACKAGE
import com.msa.petsearch.join
import com.msa.petsearch.util.libs

plugins {
    `android-common-plugin`
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = ANDROID_PACKAGE.join(
        projects.petsearchAndroid.common,
        projects.petsearchAndroid.common.compose
    )

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(libs.bundles.app.ui)

    implementation(libs.koin.androidx.compose)
    implementation(libs.kotlinx.serialization)

    implementation(projects.petsearchShared.core.util)
    implementation(projects.petsearchShared.resources)
}
