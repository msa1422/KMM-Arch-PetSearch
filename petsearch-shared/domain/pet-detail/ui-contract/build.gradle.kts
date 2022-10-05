@file:Suppress("UnstableApiUsage")

import com.msa.petsearch.PackageNameAccessor.UI_CONTRACT_PET_DETAIL
import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
}

android {
    namespace = UI_CONTRACT_PET_DETAIL
}

dependencies {
    androidMainImplementation(libs.androidx.lifecycle.viewmodel.compose)

    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.koin.core)

    commonMainImplementation(projects.petsearchShared.core.entity)
    commonMainImplementation(projects.petsearchShared.core.util)

    commonMainImplementation(projects.petsearchShared.domain.petDetail.dataSource)
}
