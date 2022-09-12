@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.util.libs

plugins {
    `kmm-shared-module-plugin`
    id("io.realm.kotlin")
}

android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

dependencies {
    commonMainImplementation(projects.shared.domainCore.entity)
    commonMainImplementation(projects.shared.domainCore.util)

    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.realm.kotlin)
}
