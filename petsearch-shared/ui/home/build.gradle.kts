@file:Suppress("DSL_SCOPE_VIOLATION")

import com.msa.petsearch.SHARED_PACKAGE
import com.msa.petsearch.join
import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.ksp)
    alias(libs.plugins.nativecoroutines)
}

android {
    namespace = SHARED_PACKAGE.join(
        projects.petsearchShared.ui,
        projects.petsearchShared.ui.home
    )
}

dependencies {
    androidMainImplementation(libs.androidx.lifecycle.viewmodel.compose)
    androidMainImplementation(libs.koin.androidx.compose)

    commonMainImplementation(libs.kermit.log)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.koin.core.coroutines)
    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.kuuuurt.multiplatform.paging)
    commonMainImplementation(projects.petsearchShared.core.entity)
    commonMainImplementation(projects.petsearchShared.core.util)
    commonMainImplementation(projects.petsearchShared.domain.home)

    // For injecting a FakeRepository into the use-cases
    commonTestImplementation(projects.petsearchShared.data.repository)
    commonTestImplementation(projects.petsearchShared.core.test)
}
