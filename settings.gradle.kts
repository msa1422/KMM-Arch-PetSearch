enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "PetFinder-Showcase"

include(
    ":android",
    ":shared",
    ":shared:domain-core",
    ":shared:domain-core:entity",
    ":shared:domain-core:util",
    ":shared:data",
    ":shared:data:infrastructure",
    ":shared:data:infrastructure:network",
    ":shared:data:infrastructure:cache",
    ":shared:data:infrastructure:preferences",
    ":shared:data:repository",
    ":shared:data:repository:pet-detail",
    ":shared:data:repository:home",
    ":shared:domain",
    ":shared:domain:home",
    ":shared:domain:home:data-source",
    ":shared:domain:home:ui-contract",
    ":shared:domain:pet-detail",
    ":shared:domain:pet-detail:data-source",
    ":shared:domain:pet-detail:ui-contract",
    ":shared:domain-core:resources",
    ":android:app",
    ":android:activity",
    ":android:activity",
    ":android:common",
    ":android:common:compose",
    ":android:common:resources",
    ":android:features",
    ":android:features:home",
    ":android:features:pet-detail"
)
