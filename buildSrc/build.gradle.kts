@file:Suppress("UnstableApiUsage")

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.gradlePlugin.kotlin)
    implementation(libs.gradlePlugin.buildTools)
    implementation(libs.gradlePlugin.dependency.check)
    implementation(libs.gradlePlugin.buildkonfig)
    implementation(libs.gradlePlugin.detekt)
}
