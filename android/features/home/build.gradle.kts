@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.util.libs

plugins {
    `android-ui-plugin`
}

dependencies {

    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)
    implementation(libs.androidx.compose.paging)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.kuuuurt.multiplatform.paging)

    implementation(projects.android.common.compose)
    implementation(projects.android.common.resources)

    implementation(projects.shared.domainCore.entity)
    implementation(projects.shared.domainCore.util)
    implementation(projects.shared.domainCore.resources)

    implementation(projects.shared.domain.home.uiContract)
}
