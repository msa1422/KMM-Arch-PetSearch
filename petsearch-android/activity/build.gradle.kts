@file:Suppress("UnstableApiUsage")

import com.msa.petsearch.PackageNameAccessor.ANDROID_ACTIVITY
import com.msa.petsearch.util.libs

plugins {
    `android-ui-plugin`
}

android {
    namespace = ANDROID_ACTIVITY
}

dependencies {
    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)

    api(projects.petsearchAndroid.common.compose)

    api(projects.petsearchAndroid.features.home)
    api(projects.petsearchAndroid.features.petDetail)

    implementation(projects.petsearchShared.resources)
    implementation(projects.petsearchShared.core.util)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
