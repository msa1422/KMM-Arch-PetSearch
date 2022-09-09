package com.petsapp.petfinder.checks

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
    checkForGradleUpdate = true
    outputFormatter = "plain"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
}
