@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.util.libs

plugins {
    `kmm-shared-module-plugin`
}

dependencies {

    commonMainImplementation(projects.petsearchShared.domainCore.entity)
    commonMainImplementation(projects.petsearchShared.domainCore.util)

    commonMainImplementation(projects.petsearchShared.domain.home.dataSource)

    commonMainImplementation(projects.petsearchShared.data.infrastructure.network)
    commonMainImplementation(projects.petsearchShared.data.infrastructure.cache)

    commonMainImplementation(libs.koin.core)

}
