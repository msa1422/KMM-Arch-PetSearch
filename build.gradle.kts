@file:Suppress("UnstableApiUsage")

import com.petsapp.petfinder.util.libs
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm")
    //id("io.gitlab.arturbosch.detekt")
}

buildscript {
    repositories.applyDefault()

    dependencies {
        classpath(libs.bundles.gradlePlugins)
    }
}

allprojects {
    repositories.applyDefault()
    plugins.apply("io.gitlab.arturbosch.detekt")
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

// DuplicateTaskException: Cannot add task 'clean' as a task with that name already exists.
/* tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}*/
