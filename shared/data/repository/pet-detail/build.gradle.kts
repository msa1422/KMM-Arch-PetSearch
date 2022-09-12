@file:Suppress("UnstableApiUsage")

plugins {
    `kmm-shared-module-plugin`
}

android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}
