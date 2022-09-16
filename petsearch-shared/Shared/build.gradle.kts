@file:Suppress("UnstableApiUsage")

import com.msa.petsearch.PackageNameAccessor.SHARED_PACKAGE
import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode
import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
    kotlin("native.cocoapods")
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.0"
        podfile = project.file("${project.rootDir}/petsearch-ios/Podfile")
        framework {
            isStatic = true
            baseName = "Shared"

            export(projects.petsearchShared.domainCore.entity)
            export(projects.petsearchShared.domainCore.util)
            export(projects.petsearchShared.domainCore.resources)
            export(projects.petsearchShared.domain.home.uiContract)
            export(projects.petsearchShared.domain.petDetail.uiContract)

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
    commonMainImplementation(libs.moko.resources)

    commonMainImplementation(projects.petsearchShared.domain.home.uiContract)
    commonMainImplementation(projects.petsearchShared.domain.petDetail.uiContract)

    commonMainImplementation(projects.petsearchShared.data.repository.home)
    commonMainImplementation(projects.petsearchShared.data.repository.petDetail)

    commonMainImplementation(projects.petsearchShared.data.infrastructure.network)
    commonMainImplementation(projects.petsearchShared.data.infrastructure.cache)
    commonMainImplementation(projects.petsearchShared.data.infrastructure.preferences)

    commonMainImplementation(projects.petsearchShared.domainCore.util)


    iosMainApi(projects.petsearchShared.domainCore.entity)
    iosMainApi(projects.petsearchShared.domainCore.util)
    iosMainApi(projects.petsearchShared.domainCore.resources)
    iosMainApi(projects.petsearchShared.domain.home.uiContract)
    iosMainApi(projects.petsearchShared.domain.petDetail.uiContract)
    iosMainApi(libs.kermit.log)
    iosMainApi(libs.moko.resources)
    iosMainApi(libs.kuuuurt.multiplatform.paging)
}

multiplatformResources {
    multiplatformResourcesPackage = SHARED_PACKAGE
    multiplatformResourcesClassName = "MR"
}