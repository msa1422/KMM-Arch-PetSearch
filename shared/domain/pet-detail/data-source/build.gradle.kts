plugins {
    `kmm-shared-module-plugin`
}

android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

dependencies {
    commonMainImplementation(projects.shared.domainCore.entity)
    commonMainImplementation(projects.shared.domainCore.util)
}
