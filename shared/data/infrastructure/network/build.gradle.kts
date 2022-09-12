@file:Suppress("UnstableApiUsage")

import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import com.petsapp.petfinder.extensions.getApiProperty
import com.petsapp.petfinder.util.libs
import com.petsapp.petfinder.util.PackageNameAccessor.DATA_INFRASTRUCTURE_PACKAGE

plugins {
    `kmm-shared-module-plugin`
    kotlin("plugin.serialization")
    id("com.codingfeline.buildkonfig")
}

android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

dependencies {

    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.bundles.ktor.common)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.kermit.log)

    commonMainImplementation(projects.shared.domainCore.entity)
    commonMainImplementation(projects.shared.domainCore.util)

    androidMainImplementation(libs.ktor.client.okHttp)

    iosMainImplementation(libs.ktor.client.darwin)
}

buildkonfig {
    packageName = DATA_INFRASTRUCTURE_PACKAGE
    defaultConfigs {
        buildConfigField(STRING, "PETFINDER_API_KEY", getApiProperty("API_KEY"))
        buildConfigField(STRING, "PETFINDER_SECRET", getApiProperty("API_SECRET"))
    }
}
