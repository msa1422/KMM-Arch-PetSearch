@file:Suppress("UnstableApiUsage")

import com.msa.petsearch.util.libs

plugins {
    `android-ui-plugin`
}

dependencies {
    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)
    implementation(libs.androidx.compose.material)
    implementation(libs.accompanist.pager)

    implementation(projects.petsearchAndroid.common.compose)
    implementation(projects.petsearchAndroid.common.resources)

    implementation(projects.petsearchShared.core.entity)
    implementation(projects.petsearchShared.core.util)
    implementation(projects.petsearchShared.resources)

    implementation(projects.petsearchShared.domain.petDetail.uiContract)
}
