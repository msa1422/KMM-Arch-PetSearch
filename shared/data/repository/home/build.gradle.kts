@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.constants.SharedModule
import com.petsapp.petfinder.util.libs

plugins {
    `kmm-shared-module-plugin`
}

android {
    namespace = SharedModule.Data.Repository.Home.PACKAGE
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

dependencies {

    commonMainImplementation(project(SharedModule.DomainCore.Entity.MODULE))
    commonMainImplementation(project(SharedModule.DomainCore.Util.MODULE))

    commonMainImplementation(project(SharedModule.Domain.Home.DataSource.MODULE))

    commonMainImplementation(project(SharedModule.Data.Infrastructure.Cache.MODULE))
    commonMainImplementation(project(SharedModule.Data.Infrastructure.Network.MODULE))

    commonMainImplementation(libs.koin.core)

}