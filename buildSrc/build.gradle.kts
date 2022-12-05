@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://plugins.gradle.org/m2/")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.gradlePlugin.kotlin)
    implementation(libs.gradlePlugin.buildTools)
    implementation(libs.gradlePlugin.kotest.framework)
    implementation(libs.gradlePlugin.dependency.check)
    implementation(libs.gradlePlugin.detekt)
    implementation(libs.gradlePlugin.spotless)
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = libs.versions.java.get().toString()
        languageVersion = libs.versions.kt.get().toString()
    }
}
