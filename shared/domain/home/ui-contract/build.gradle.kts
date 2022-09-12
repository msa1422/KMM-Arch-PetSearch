@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.util.libs

plugins {
    `kmm-shared-module-plugin`
}

android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

dependencies {

    androidMainImplementation(libs.androidx.lifecycle.viewmodel.compose)

    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.kuuuurt.multiplatform.paging)
    commonMainImplementation(libs.kermit.log)

    commonMainImplementation(projects.shared.domainCore.entity)
    commonMainImplementation(projects.shared.domainCore.util)
    commonMainImplementation(projects.shared.domainCore.resources)

    commonMainImplementation(projects.shared.domain.home.dataSource)
}
