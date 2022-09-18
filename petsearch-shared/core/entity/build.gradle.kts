import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
    kotlin("plugin.serialization")
}

dependencies {
    commonMainImplementation(libs.kotlinx.serialization)
}
