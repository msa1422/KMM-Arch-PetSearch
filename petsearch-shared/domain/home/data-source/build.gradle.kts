plugins {
    `kmm-shared-module-plugin`
}

dependencies {
    commonMainImplementation(projects.petsearchShared.core.entity)
    commonMainImplementation(projects.petsearchShared.core.util)
}
