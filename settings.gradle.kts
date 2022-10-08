enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        maven("https://plugins.gradle.org/m2/")
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
    }
}

rootProject.name = "PetFinder-Showcase"

include(
    ":petsearch-shared",
    ":petsearch-shared:Shared",
    ":petsearch-shared:resources",
    ":petsearch-shared:core",
    ":petsearch-shared:core:entity",
    ":petsearch-shared:core:util",
    ":petsearch-shared:data",
    ":petsearch-shared:data:infrastructure",
    ":petsearch-shared:data:infrastructure:network",
    ":petsearch-shared:data:infrastructure:cache",
    ":petsearch-shared:data:infrastructure:preferences",
    ":petsearch-shared:data:repository",
    ":petsearch-shared:data:repository:pet-detail",
    ":petsearch-shared:data:repository:home",
    ":petsearch-shared:domain",
    ":petsearch-shared:domain:home",
    ":petsearch-shared:domain:home:data-source",
    ":petsearch-shared:domain:home:ui-contract",
    ":petsearch-shared:domain:pet-detail",
    ":petsearch-shared:domain:pet-detail:data-source",
    ":petsearch-shared:domain:pet-detail:ui-contract",
    ":petsearch-android",
    ":petsearch-android:app",
    ":petsearch-android:activity",
    ":petsearch-android:activity",
    ":petsearch-android:common",
    ":petsearch-android:common:compose",
    ":petsearch-android:common:resources",
    ":petsearch-android:features",
    ":petsearch-android:features:home",
    ":petsearch-android:features:pet-detail"
)
include(":petsearch-shared:core:test")
