@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories.applyDefault()
}

allprojects {
    repositories.applyDefault()

    plugins.apply("com.msa.petsearch.checks.detekt")
    plugins.apply("com.msa.petsearch.checks.ktlint")
    plugins.apply("com.msa.petsearch.checks.spotless")
}

subprojects {
    tasks.withType<KotlinCompile>().configureEach {
        with(kotlinOptions) {

            jvmTarget = JavaVersion.toVersion(JavaVersion.VERSION_11).toString()

            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                "-opt-in=kotlin.time.ExperimentalTime",
                "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=androidx.compose.ui.unit.ExperimentalUnitApi",
                "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                "-opt-in=kotlinx.serialization.ExperimentalSerializationApi"
            )
        }
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

// DuplicateTaskException: Cannot add task 'clean' as a task with that name already exists.
/* tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}*/
