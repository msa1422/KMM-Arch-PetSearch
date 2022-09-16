@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.util.libs

plugins {
    `android-ui-plugin`
}

dependencies {
    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)

    api(projects.petsearchAndroid.common.compose)

    api(projects.petsearchAndroid.features.home)
    api(projects.petsearchAndroid.features.petDetail)

    implementation(projects.petsearchShared.domainCore.resources)
    implementation(projects.petsearchShared.domainCore.util)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
