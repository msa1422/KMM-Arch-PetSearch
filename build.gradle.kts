@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.util.libs
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.github.ben-manes.versions")
    id("org.jetbrains.kotlin.jvm")
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://plugins.gradle.org/m2/")
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
    }
    dependencies {
        classpath(libs.bundles.gradlePlugins)
    }
}

allprojects {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
    }
}

subprojects {
    tasks.withType<KotlinCompile>().configureEach {
        with(kotlinOptions) {

            jvmTarget = JavaVersion.toVersion(JavaVersion.VERSION_11).toString()

            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=kotlin.time.ExperimentalTime",
                "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=androidx.compose.ui.unit.ExperimentalUnitApi",
                "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                "-opt-in=kotlinx.serialization.ExperimentalSerializationApi"
            )
        }
    }
}

// Removed on June 26, 2022
// DuplicateTaskException: Cannot add task 'clean' as a task with that name already exists.
/* tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}*/
