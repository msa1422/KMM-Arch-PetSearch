@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")

import com.msa.petsearch.util.libs
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("io.kotest.multiplatform")
    id("com.msa.petsearch.checks.detekt")
    id("com.msa.petsearch.checks.ktlint")
    id("com.msa.petsearch.checks.spotless")
}

version = libs.versions.project.version.get()

repositories {
    applyDefault()
}

kotlin {
    applyDefaultHierarchyTemplate()

    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets.all {
        languageSettings.apply {
            optIn("kotlin.RequiresOptIn")
            optIn("kotlin.experimental.ExperimentalObjCName")
            optIn("kotlin.time.ExperimentalTime")
            optIn("kotlinx.cinterop.ExperimentalForeignApi")
            optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            optIn("kotlinx.coroutines.FlowPreview")
            optIn("kotlinx.serialization.ExperimentalSerializationApi")
        }
    }

    sourceSets {
        val commonTest by getting {
            dependencies {
                implementation(project.dependencies.platform(libs.koin.bom))

                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.koin.test)
                implementation(libs.testing.turbine)
                implementation(libs.testing.kotest.framework.engine)
                implementation(libs.testing.kotest.framework.datatest)
                implementation(libs.testing.kotest.assertions.core)
                implementation(libs.testing.kotest.assertions.json)
                implementation(libs.testing.kotest.property)
            }
        }

        val androidUnitTest by getting {
            dependencies {
                implementation(libs.testing.kotest.runner.junit5)
            }
        }
    }

    jvmToolchain(libs.versions.java.get().toInt())
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        consumerProguardFiles("proguard-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()

    filter {
        isFailOnNoMatchingTests = false
    }

    testLogging {
        showExceptions = true
        showStandardStreams = true
        events = setOf(
            org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
            org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
        )
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}

// Workaround for Moko-Resources bug where it is unable to find a task named 'testClasses'
task("testClasses").doLast {
    println("This is a dummy testClasses task")
}
