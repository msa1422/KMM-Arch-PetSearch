@file:Suppress("UnstableApiUsage", "UNUSED_VARIABLE")

import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.msa.petsearch.extensions.BuildTypeBenchmark
import com.msa.petsearch.extensions.BuildTypeDebug
import com.msa.petsearch.extensions.BuildTypeRelease
import com.msa.petsearch.extensions.getKeystoreProperty
import com.msa.petsearch.util.libs
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
    id("com.msa.petsearch.checks.detekt")
    id("com.msa.petsearch.checks.ktlint")
    id("com.msa.petsearch.checks.spotless")
    id("com.msa.petsearch.checks.dependency-updates")
}

repositories {
    applyDefault()
}

android {

    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.msa.petsearch"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        multiDexEnabled = true
    }

    applicationVariants.all {
        val variant = this
        variant.outputs
            .map { it as BaseVariantOutputImpl }
            .forEach { output ->
                output.outputFileName = "app-${variant.baseName}-${variant.buildType.name}-${variant.versionName}.apk"
            }
    }

    signingConfigs {
        val release by creating {
            storeFile = file(getKeystoreProperty("STORE_FILE"))
            storePassword = getKeystoreProperty("STORE_PASSWORD")
            keyAlias = getKeystoreProperty("KEY_ALIAS")
            keyPassword = getKeystoreProperty("KEY_PASSWORD")
        }
    }

    buildTypes {
        val debug by getting {
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            enableUnitTestCoverage = BuildTypeDebug.enableUnitTestCoverage
            enableAndroidTestCoverage = BuildTypeDebug.enableAndroidTestCoverage
        }

        val release by getting {
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            enableUnitTestCoverage = BuildTypeRelease.enableUnitTestCoverage
            enableAndroidTestCoverage = BuildTypeRelease.enableAndroidTestCoverage
        }

        val benchmark by creating {
            initWith(release)
            matchingFallbacks.add("release")

            signingConfig = debug.signingConfig

            proguardFiles("benchmark-rules.pro")
            applicationIdSuffix = BuildTypeBenchmark.applicationIdSuffix
            versionNameSuffix = BuildTypeBenchmark.versionNameSuffix
            enableUnitTestCoverage = BuildTypeBenchmark.enableUnitTestCoverage
            enableAndroidTestCoverage = BuildTypeBenchmark.enableAndroidTestCoverage
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    sourceSets.all {
        java.srcDirs("src/$name/kotlin")
    }
}

dependencies {
    debugImplementation(libs.squareup.leakcanary)
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = libs.versions.java.get().toString()
        languageVersion = libs.versions.kt.get().toString()
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
