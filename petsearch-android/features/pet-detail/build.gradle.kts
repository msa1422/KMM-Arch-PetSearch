@file:Suppress("UnstableApiUsage")

import com.msa.petsearch.PackageNameAccessor.ANDROID_FEATURE_PET_DETAIL
import com.msa.petsearch.util.libs

plugins {
    `android-ui-plugin`
}

android {
    namespace = ANDROID_FEATURE_PET_DETAIL
}

dependencies {
    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)
    implementation(libs.androidx.compose.material)
    implementation(libs.accompanist.pager)

    implementation(projects.petsearchAndroid.common.compose)

    implementation(projects.petsearchShared.core.entity)
    implementation(projects.petsearchShared.core.util)
    implementation(projects.petsearchShared.resources)

    implementation(projects.petsearchShared.domain.petDetail.uiContract)
}
