@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.util.libs

plugins {
    `android-app-plugin`
}

dependencies {
    implementation(projects.petsearchAndroid.activity)
    implementation(projects.petsearchShared.shared)
    implementation(libs.koin.core)
    implementation(libs.coil.compose)
}
