@file:Suppress("UnstableApiUsage")

import com.msa.petsearch.util.libs

plugins {
    `android-ui-plugin`
}

dependencies {

    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)
    implementation(libs.androidx.compose.paging)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.kuuuurt.multiplatform.paging)

    implementation(projects.petsearchAndroid.common.compose)
    implementation(projects.petsearchAndroid.common.resources)

    implementation(projects.petsearchShared.domainCore.entity)
    implementation(projects.petsearchShared.domainCore.util)
    implementation(projects.petsearchShared.domainCore.resources)

    implementation(projects.petsearchShared.domain.home.uiContract)
}
