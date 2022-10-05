@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.msa.petsearch.PackageNameAccessor
import com.msa.petsearch.extensions.BuildType
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
}

repositories {
    applyDefault()
}

android {

    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = PackageNameAccessor.javaClass.packageName
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
        create(BuildType.RELEASE) {
            storeFile = file(getKeystoreProperty("STORE_FILE"))
            storePassword = getKeystoreProperty("STORE_PASSWORD")
            keyAlias = getKeystoreProperty("KEY_ALIAS")
            keyPassword = getKeystoreProperty("KEY_PASSWORD")
        }
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            signingConfig = signingConfigs.getByName(BuildType.RELEASE)
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            enableUnitTestCoverage = BuildTypeRelease.enableUnitTestCoverage
        }
        getByName(BuildType.DEBUG) {
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            enableUnitTestCoverage = BuildTypeDebug.enableUnitTestCoverage
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }

    dependencies {
        debugImplementation(libs.squareup.leakcanary)
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = libs.versions.java.get().toString()
        languageVersion = libs.versions.kt.get().toString()
    }
}
