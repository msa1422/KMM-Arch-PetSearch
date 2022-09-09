package com.petsapp.petfinder.checks

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

apply<DetektPlugin>()

configure<DetektExtension> {

    parallel = true
    buildUponDefaultConfig = true
    allRules = false
    source = project.files("src/main/kotlin")
    config = files("${rootProject.projectDir}/tooling/config/detekt.yml")

}

tasks.withType<Detekt>().configureEach {
    reports {

        html.required.set(true)
        html.outputLocation.set(rootProject.file("build/reports/detekt/report.html"))

        xml.required.set(true)
        xml.outputLocation.set(rootProject.file("build/reports/detekt/report.xml"))

        // txt.required.set(true)
        // sarif.required.set(true)
        // md.required.set(true) // simple Markdown format
    }
}


tasks.withType<Detekt>().configureEach {
    jvmTarget = JavaVersion.VERSION_11.toString()
}
tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = JavaVersion.VERSION_11.toString()
}
