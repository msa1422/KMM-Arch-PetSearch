@file:Suppress("UnstableApiUsage")

import com.msa.petsearch.util.libs

plugins {
    id("com.android.library")
    kotlin("android")
    id("com.msa.petsearch.checks.detekt")
    id("com.msa.petsearch.checks.ktlint")
    id("com.msa.petsearch.checks.spotless")
    id("org.jetbrains.kotlin.plugin.compose")
}

repositories {
    applyDefault()
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        consumerProguardFiles("proguard-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }

    sourceSets.all {
        java.srcDirs("src/$name/kotlin")
    }
}

kotlin {
    sourceSets.all {
        languageSettings.apply {
            optIn("kotlin.RequiresOptIn")
            optIn("androidx.compose.animation.ExperimentalAnimationApi")
            optIn("androidx.compose.material3.ExperimentalMaterial3Api")
            optIn("androidx.compose.ui.unit.ExperimentalUnitApi")
        }
    }

    jvmToolchain(libs.versions.java.get().toInt())
}

dependencies {
    api(libs.bundles.app.ui)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
