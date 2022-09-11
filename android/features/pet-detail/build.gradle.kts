@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.constants.AndroidModule
import com.petsapp.petfinder.constants.SharedModule
import com.petsapp.petfinder.util.libs

plugins {
    `android-ui-plugin`
}

android {
    namespace = AndroidModule.Features.PetDetail.PACKAGE
}


dependencies {

    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)
    implementation(libs.androidx.compose.material)
    implementation(libs.accompanist.pager)

    implementation(project(AndroidModule.Common.Compose.MODULE))
    implementation(project(AndroidModule.Common.Resources.MODULE))

    implementation(project(SharedModule.DomainCore.Entity.MODULE))
    implementation(project(SharedModule.DomainCore.Util.MODULE))
    implementation(project(SharedModule.DomainCore.Resources.MODULE))

    implementation(project(SharedModule.Domain.PetDetail.UiContract.MODULE))

}
