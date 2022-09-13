plugins {
    `kmm-shared-module-plugin`
}

dependencies {
    commonMainImplementation(projects.shared.domainCore.entity)
    commonMainImplementation(projects.shared.domainCore.util)
}
