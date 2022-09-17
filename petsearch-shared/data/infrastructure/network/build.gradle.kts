@file:Suppress("UnstableApiUsage")

import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import com.msa.petsearch.extensions.getApiProperty
import com.msa.petsearch.util.libs
import com.msa.petsearch.PackageNameAccessor.DATA_INFRASTRUCTURE_NETWORK_PACKAGE

plugins {
    `kmm-shared-module-plugin`
    kotlin("plugin.serialization")
    id("com.codingfeline.buildkonfig")
}

dependencies {
    commonMainImplementation(projects.petsearchShared.domainCore.util)
    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.kermit.log)
    commonMainImplementation(libs.bundles.ktor.common)

    androidMainImplementation(libs.ktor.client.okHttp)

    iosMainImplementation(libs.ktor.client.darwin)
}

buildkonfig {
    packageName = DATA_INFRASTRUCTURE_NETWORK_PACKAGE
    defaultConfigs {
        buildConfigField(STRING, "PETFINDER_API_KEY", getApiProperty("API_KEY"))
        buildConfigField(STRING, "PETFINDER_SECRET", getApiProperty("API_SECRET"))
    }
}
