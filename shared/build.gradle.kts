@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.constants.SharedModule
import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode
import com.petsapp.petfinder.util.libs

plugins {
    `kmm-shared-module-plugin`
    kotlin("native.cocoapods")
}

android {
    namespace = SharedModule.PACKAGE
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

            export(project(SharedModule.DomainCore.Entity.MODULE))
            export(project(SharedModule.DomainCore.Util.MODULE))
            export(project(SharedModule.DomainCore.Resources.MODULE))
            export(project(SharedModule.Domain.PetDetail.UiContract.MODULE))
            export(project(SharedModule.Domain.Home.UiContract.MODULE))

            export(libs.kermit.log)
            export(libs.moko.resources)

            transitiveExport = true
            embedBitcode(BitcodeEmbeddingMode.BITCODE)
        }
    }
}

dependencies {

    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.koin.core)

    commonMainImplementation(project(SharedModule.Domain.Home.UiContract.MODULE))
    commonMainImplementation(project(SharedModule.Domain.PetDetail.UiContract.MODULE))

    commonMainImplementation(project(SharedModule.Data.Repository.Home.MODULE))
    commonMainImplementation(project(SharedModule.Data.Repository.PetDetail.MODULE))

    commonMainImplementation(project(SharedModule.Data.Infrastructure.Network.MODULE))
    commonMainImplementation(project(SharedModule.Data.Infrastructure.Cache.MODULE))
    commonMainImplementation(project(SharedModule.Data.Infrastructure.Preferences.MODULE))

    commonMainImplementation(project(SharedModule.DomainCore.Util.MODULE))

    iosMainApi(project(SharedModule.DomainCore.Entity.MODULE))
    iosMainApi(project(SharedModule.DomainCore.Util.MODULE))
    iosMainApi(project(SharedModule.DomainCore.Resources.MODULE))
    iosMainApi(project(SharedModule.Domain.Home.UiContract.MODULE))
    iosMainApi(project(SharedModule.Domain.PetDetail.UiContract.MODULE))
    iosMainApi(libs.kermit.log)
    iosMainApi(libs.moko.resources)


}