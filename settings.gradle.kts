pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "PetFinder-Showcase"
include(":android")
include(":shared")
include(":shared:domain-core")
include(":shared:domain-core:entity")
