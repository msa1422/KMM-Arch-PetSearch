@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.util.libs

plugins {
    `kmm-shared-module-plugin`
}

android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

dependencies {

    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.multiplatformSettings.noArg)
    commonMainImplementation(libs.multiplatformSettings.coroutines)
    commonMainImplementation(libs.kermit.log)

    commonMainImplementation(projects.shared.domainCore.entity)
    commonMainImplementation(projects.shared.domainCore.util)

}
