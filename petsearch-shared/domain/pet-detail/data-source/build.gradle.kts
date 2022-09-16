plugins {
    `kmm-shared-module-plugin`
}

dependencies {
    commonMainImplementation(projects.petsearchShared.domainCore.entity)
    commonMainImplementation(projects.petsearchShared.domainCore.util)
}
