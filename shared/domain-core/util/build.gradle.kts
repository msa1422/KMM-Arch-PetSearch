@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.util.libs

plugins {
    `kmm-shared-module-plugin`
    kotlin("plugin.serialization")
}

android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}


dependencies {

    commonMainImplementation(libs.squareup.okio)
    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.uuid.multiplatform)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.kotlinx.serialization)

    commonMainImplementation(libs.kermit.log)
    //commonMainImplementation(libs.kermit.crashlytics)

    androidMainImplementation(libs.androidx.lifecycle.viewmodel.compose)

}
