@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.constants.SharedModule
import com.petsapp.petfinder.util.libs

plugins {
    `kmm-shared-module-plugin`
}

android {
    namespace = SharedModule.Domain.PetDetail.UiContract.PACKAGE
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

dependencies {

    androidMainImplementation(libs.androidx.lifecycle.viewmodel.compose)

    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.koin.core)

    commonMainImplementation(project(SharedModule.DomainCore.Entity.MODULE))
    commonMainImplementation(project(SharedModule.DomainCore.Util.MODULE))
    commonMainImplementation(project(SharedModule.DomainCore.Resources.MODULE))

    commonMainImplementation(project(SharedModule.Domain.PetDetail.DataSource.MODULE))

}