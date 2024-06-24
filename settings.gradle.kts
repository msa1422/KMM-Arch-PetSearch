enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        maven("https://plugins.gradle.org/m2/")
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
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
    ":petsearch-shared:core:test",
    ":petsearch-shared:core:util",
    ":petsearch-shared:data",
    ":petsearch-shared:data:infra",
    ":petsearch-shared:data:infra:network",
    ":petsearch-shared:data:infra:cache",
    ":petsearch-shared:data:infra:preferences",
    ":petsearch-shared:data:source",
    ":petsearch-shared:data:repository",
    ":petsearch-shared:domain",
    ":petsearch-shared:domain:home",
    ":petsearch-shared:domain:petdetail",
    ":petsearch-shared:ui",
    ":petsearch-shared:ui:home",
    ":petsearch-shared:ui:petdetail",
    ":petsearch-android",
    ":petsearch-android:app",
    ":petsearch-android:activity",
    ":petsearch-android:common",
    ":petsearch-android:common:components",
    ":petsearch-android:common:compose",
    ":petsearch-android:features",
    ":petsearch-android:features:home",
    ":petsearch-android:features:petdetail"
)
