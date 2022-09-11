@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.constants.SharedModule
import com.petsapp.petfinder.util.libs

plugins {
    `kmm-shared-module-plugin`
    id("io.realm.kotlin")
}

android {
    namespace = SharedModule.Data.Infrastructure.Cache.PACKAGE
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

dependencies {
    commonMainImplementation(project(SharedModule.DomainCore.Entity.MODULE))
    commonMainImplementation(project(SharedModule.DomainCore.Util.MODULE))

    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.realm.kotlin)
}
