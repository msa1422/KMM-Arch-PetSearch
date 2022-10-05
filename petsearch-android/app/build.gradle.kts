@file:Suppress("UnstableApiUsage")

import com.msa.petsearch.PackageNameAccessor.ANDROID_APP
import com.msa.petsearch.util.libs

plugins {
    `android-app-plugin`
}

android {
    namespace = ANDROID_APP
}

dependencies {
    implementation(projects.petsearchAndroid.activity)
    implementation(projects.petsearchShared.shared)
    implementation(libs.koin.core)
    implementation(libs.coil.compose)
}
