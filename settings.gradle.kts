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
include(":shared:domain-core:util")
include(":shared:data")
include(":shared:data:infrastructure")
include(":shared:data:infrastructure:network")
