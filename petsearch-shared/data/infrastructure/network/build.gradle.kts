@file:Suppress("DSL_SCOPE_VIOLATION")

import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import com.msa.petsearch.extensions.getApiProperty
import com.msa.petsearch.util.libs
import com.msa.petsearch.PackageNameAccessor.DATA_INFRASTRUCTURE_NETWORK

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.codingfeline.buildkonfig)
}

android {
    namespace = DATA_INFRASTRUCTURE_NETWORK
}

dependencies {
    commonMainImplementation(projects.petsearchShared.core.util)
    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.kermit.log)
    commonMainImplementation(libs.bundles.ktor.common)

    androidMainImplementation(libs.ktor.client.okHttp)

    iosMainImplementation(libs.ktor.client.darwin)
}

buildkonfig {
    packageName = DATA_INFRASTRUCTURE_NETWORK
    defaultConfigs {
        buildConfigField(STRING, "PETFINDER_API_KEY", getApiProperty("API_KEY"))
        buildConfigField(STRING, "PETFINDER_SECRET", getApiProperty("API_SECRET"))
    }
}
