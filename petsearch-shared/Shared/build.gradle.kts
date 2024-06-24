@file:Suppress("DSL_SCOPE_VIOLATION")

import co.touchlab.skie.configuration.EnumInterop
import co.touchlab.skie.configuration.FlowInterop
import co.touchlab.skie.configuration.SealedInterop
import co.touchlab.skie.configuration.SuspendInterop
import com.msa.petsearch.SHARED_PACKAGE
import com.msa.petsearch.util.libs
import dev.icerock.gradle.MRVisibility

plugins {
    `kmm-shared-module-plugin`
    kotlin("native.cocoapods")
    alias(libs.plugins.moko.resources)
    alias(libs.plugins.touchlab.skie)
}

android {
    namespace = SHARED_PACKAGE
}

kotlin {
    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.0"
        podfile = project.file("${project.rootDir}/petsearch-ios/Podfile")
        framework {
            isStatic = true
            baseName = projects.petsearchShared.shared.name

            export(projects.petsearchShared.core.entity)
            export(projects.petsearchShared.core.util)
            export(projects.petsearchShared.resources)
            export(projects.petsearchShared.ui.home)
            export(projects.petsearchShared.ui.petdetail)

            export(libs.kermit.log)
            export(libs.koin.core)
            export(libs.moko.resources)
        }
    }
}

dependencies {
    commonMainImplementation(platform(libs.koin.bom))
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.koin.core.coroutines)
    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.moko.resources)
    commonMainImplementation(projects.petsearchShared.core.util)
    commonMainImplementation(projects.petsearchShared.data.source)
    commonMainImplementation(projects.petsearchShared.ui.home)
    commonMainImplementation(projects.petsearchShared.ui.petdetail)

    iosMainApi(libs.kermit.log)
    iosMainApi(libs.koin.core)
    iosMainApi(libs.kuuuurt.multiplatform.paging)
    iosMainApi(libs.moko.resources)
    iosMainApi(projects.petsearchShared.core.entity)
    iosMainApi(projects.petsearchShared.core.util)
    iosMainApi(projects.petsearchShared.resources)
    iosMainApi(projects.petsearchShared.ui.home)
    iosMainApi(projects.petsearchShared.ui.petdetail)
}

multiplatformResources {
    resourcesPackage.set(SHARED_PACKAGE)
    resourcesClassName.set("MR")
    resourcesVisibility.set(MRVisibility.Internal)
}

skie {
    features {
        group {
            FlowInterop.Enabled(false)
            SuspendInterop.Enabled(false)
            SealedInterop.Enabled(false)
            EnumInterop.Enabled(false)
        }
    }

    analytics {
        enabled.set(false)
        disableUpload.set(true)
    }
}
