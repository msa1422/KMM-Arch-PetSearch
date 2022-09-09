@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.constants.AndroidModule
import com.petsapp.petfinder.constants.SharedModule
import com.petsapp.petfinder.util.libs

plugins {
    `android-ui-plugin`
}

android {
    namespace = AndroidModule.Activity.PACKAGE
}


dependencies {

    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)

    api(project(AndroidModule.Common.Compose.MODULE))

    api(project(AndroidModule.Features.Home.MODULE))
    api(project(AndroidModule.Features.PetDetail.MODULE))


    implementation(project(SharedModule.DomainCore.Util.MODULE))
    implementation(project(SharedModule.DomainCore.Resources.MODULE))


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
