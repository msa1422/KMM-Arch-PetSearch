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
    implementation(libs.gradle.plugin.android.build.tools)
    implementation(libs.gradle.plugin.dependency.check)
    implementation(libs.gradle.plugin.detekt)
    implementation(libs.gradle.plugin.kotest.framework)
    implementation(libs.gradle.plugin.kotlin)
    implementation(libs.gradle.plugin.ksp)
    implementation(libs.gradle.plugin.spotless)
}
