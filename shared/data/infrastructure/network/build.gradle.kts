@file:Suppress("UnstableApiUsage")

import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import com.petsapp.petfinder.constants.SharedModule
import com.petsapp.petfinder.extensions.getApiProperty
import com.petsapp.petfinder.util.libs

plugins {
    `kmm-shared-module-plugin`
    kotlin("plugin.serialization")
    id("com.codingfeline.buildkonfig")
}

android {
    namespace = SharedModule.Data.Infrastructure.Network.PACKAGE
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

dependencies {

    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.bundles.ktor.common)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.kermit.log)


    commonMainImplementation(project(SharedModule.DomainCore.Entity.MODULE))
    commonMainImplementation(project(SharedModule.DomainCore.Util.MODULE))

    androidMainImplementation(libs.ktor.client.okHttp)

    iosMainImplementation(libs.ktor.client.darwin)

}

buildkonfig {
    packageName = SharedModule.Data.Infrastructure.Network.PACKAGE
    defaultConfigs {
        buildConfigField(STRING, "PETFINDER_API_KEY", getApiProperty("API_KEY"))
        buildConfigField(STRING, "PETFINDER_SECRET", getApiProperty("API_SECRET"))
    }
}
