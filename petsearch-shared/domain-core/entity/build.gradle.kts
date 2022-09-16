import com.petsapp.petfinder.util.libs

plugins {
    `kmm-shared-module-plugin`
    kotlin("plugin.serialization")
}

dependencies {
    commonMainImplementation(libs.kotlinx.serialization)
}
