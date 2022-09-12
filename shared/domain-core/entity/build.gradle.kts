import com.petsapp.petfinder.util.libs

plugins {
    `kmm-shared-module-plugin`
    kotlin("plugin.serialization")
}

android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

dependencies {
    commonMainImplementation(libs.kotlinx.serialization)
}
