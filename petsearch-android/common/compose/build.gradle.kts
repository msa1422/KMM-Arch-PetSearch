@file:Suppress("DSL_SCOPE_VIOLATION")

import com.msa.petsearch.util.libs

plugins {
    `android-common-plugin`
    alias(libs.plugins.kotlin.serialization)
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.koin.androidx.compose)
    implementation(libs.kotlinx.serialization)

    implementation(libs.bundles.app.ui)

    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.accompanist.navigation.animation)

    implementation(projects.petsearchShared.core.util)
    implementation(projects.petsearchShared.resources)

    implementation(projects.petsearchAndroid.common.resources)
}
