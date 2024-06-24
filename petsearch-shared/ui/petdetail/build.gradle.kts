@file:Suppress("DSL_SCOPE_VIOLATION")

import com.msa.petsearch.SHARED_PACKAGE
import com.msa.petsearch.join
import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.ksp)
    alias(libs.plugins.kmp.nativecoroutines)
}

android {
    namespace = SHARED_PACKAGE.join(
        projects.petsearchShared.ui,
        projects.petsearchShared.ui.petdetail
    )
}

dependencies {
    androidMainImplementation(libs.androidx.lifecycle.viewmodel.compose)
    androidMainImplementation(libs.koin.androidx.compose)

    commonMainImplementation(platform(libs.koin.bom))
    commonMainImplementation(libs.kmp.observableviewmodel)
    commonMainImplementation(libs.kermit.log)
    commonMainImplementation(libs.koin.core)
    commonMainImplementation(libs.koin.core.coroutines)
    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.kuuuurt.multiplatform.paging)
    commonMainImplementation(libs.touchlab.skie.annotations)
    commonMainImplementation(projects.petsearchShared.core.entity)
    commonMainImplementation(projects.petsearchShared.core.util)
    commonMainImplementation(projects.petsearchShared.domain.petdetail)

    commonTestImplementation(projects.petsearchShared.core.test)
}
