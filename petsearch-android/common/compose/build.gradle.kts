@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.util.libs

plugins {
    `android-common-plugin`
    kotlin("plugin.serialization")
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

    implementation(projects.petsearchShared.domainCore.util)
    implementation(projects.petsearchShared.domainCore.resources)

    implementation(projects.petsearchAndroid.common.resources)
}
