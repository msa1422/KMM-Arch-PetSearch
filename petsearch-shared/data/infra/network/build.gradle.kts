@file:Suppress("DSL_SCOPE_VIOLATION")

import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import com.msa.petsearch.SHARED_PACKAGE
import com.msa.petsearch.extensions.getApiProperty
import com.msa.petsearch.join
import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.codingfeline.buildkonfig)
}

android {
    namespace = SHARED_PACKAGE.join(
        projects.petsearchShared.data,
        projects.petsearchShared.data.infra,
        projects.petsearchShared.data.infra.network
    )
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
    packageName = SHARED_PACKAGE.join(
        projects.petsearchShared.data,
        projects.petsearchShared.data.infra,
        projects.petsearchShared.data.infra.network
    )
    defaultConfigs {
        buildConfigField(STRING, "PETFINDER_API_KEY", getApiProperty("API_KEY"))
        buildConfigField(STRING, "PETFINDER_SECRET", getApiProperty("API_SECRET"))
    }
}
