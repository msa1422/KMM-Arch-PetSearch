@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.util.libs
import com.petsapp.petfinder.constants.AndroidModule
import com.petsapp.petfinder.constants.SharedModule

plugins {
    `android-app-plugin`
}

android {
    namespace = AndroidModule.App.PACKAGE
}


dependencies {

    implementation(project(AndroidModule.Activity.MODULE))

    implementation(project(SharedModule.MODULE))

    implementation(libs.koin.core)
    implementation(libs.coil.compose)

}
