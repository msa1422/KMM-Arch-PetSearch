@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.util.libs

plugins {
    `kmm-shared-module-plugin`
}

dependencies {

    androidMainImplementation(libs.androidx.lifecycle.viewmodel.compose)

    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.koin.core)

    commonMainImplementation(projects.petsearchShared.domainCore.entity)
    commonMainImplementation(projects.petsearchShared.domainCore.util)
    commonMainImplementation(projects.petsearchShared.domainCore.resources)

    commonMainImplementation(projects.petsearchShared.domain.petDetail.dataSource)
}
