@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode
import com.petsapp.petfinder.util.libs

plugins {
    `kmm-shared-module-plugin`
    kotlin("native.cocoapods")
}

android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

kotlin {
    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.0"
        podfile = project.file("../ios/Podfile")
        framework {
            isStatic = true
            baseName = "shared"

            export(projects.shared.domainCore.entity)
            export(projects.shared.domainCore.util)
            export(projects.shared.domainCore.resources)
            export(projects.shared.domain.home.uiContract)
            export(projects.shared.domain.petDetail.uiContract)

            export(libs.kermit.log)
            export(libs.moko.resources)
            export(libs.kuuuurt.multiplatform.paging)

            transitiveExport = true
            embedBitcode(BitcodeEmbeddingMode.BITCODE)
        }
    }
}

dependencies {

    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.koin.core)

    commonMainImplementation(projects.shared.domain.home.uiContract)
    commonMainImplementation(projects.shared.domain.petDetail.uiContract)

    commonMainImplementation(projects.shared.data.repository.home)
    commonMainImplementation(projects.shared.data.repository.petDetail)

    commonMainImplementation(projects.shared.data.infrastructure.network)
    commonMainImplementation(projects.shared.data.infrastructure.cache)
    commonMainImplementation(projects.shared.data.infrastructure.preferences)

    commonMainImplementation(projects.shared.domainCore.util)


    iosMainApi(projects.shared.domainCore.entity)
    iosMainApi(projects.shared.domainCore.util)
    iosMainApi(projects.shared.domainCore.resources)
    iosMainApi(projects.shared.domain.home.uiContract)
    iosMainApi(projects.shared.domain.petDetail.uiContract)
    iosMainApi(libs.kermit.log)
    iosMainApi(libs.moko.resources)
    iosMainApi(libs.kuuuurt.multiplatform.paging)
}
