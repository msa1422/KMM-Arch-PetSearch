@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.constants.AndroidModule
import com.petsapp.petfinder.constants.SharedModule
import com.petsapp.petfinder.util.libs

plugins {
    `android-ui-plugin`
}

android {
    namespace = AndroidModule.Features.Home.PACKAGE
}


dependencies {

    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)
    implementation(libs.androidx.compose.paging)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.kuuuurt.multiplatform.paging)

    implementation(project(AndroidModule.Common.Compose.MODULE))
    implementation(project(AndroidModule.Common.Resources.MODULE))

    implementation(project(SharedModule.DomainCore.Entity.MODULE))
    implementation(project(SharedModule.DomainCore.Util.MODULE))
    implementation(project(SharedModule.DomainCore.Resources.MODULE))

    implementation(project(SharedModule.Domain.Home.UiContract.MODULE))

}
