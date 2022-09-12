@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.util.libs

plugins {
    `android-app-plugin`
}

dependencies {
    implementation(projects.android.activity)
    implementation(projects.shared)
    implementation(libs.koin.core)
    implementation(libs.coil.compose)
}
