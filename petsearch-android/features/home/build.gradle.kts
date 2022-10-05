@file:Suppress("UnstableApiUsage")

import com.msa.petsearch.PackageNameAccessor.ANDROID_FEATURE_HOME
import com.msa.petsearch.util.libs

plugins {
    `android-ui-plugin`
}

android {
    namespace = ANDROID_FEATURE_HOME
}

dependencies {

    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)
    implementation(libs.androidx.compose.paging)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.kuuuurt.multiplatform.paging)

    implementation(projects.petsearchAndroid.common.compose)
    implementation(projects.petsearchAndroid.common.resources)

    implementation(projects.petsearchShared.core.entity)
    implementation(projects.petsearchShared.core.util)
    implementation(projects.petsearchShared.resources)

    implementation(projects.petsearchShared.domain.home.uiContract)
}
