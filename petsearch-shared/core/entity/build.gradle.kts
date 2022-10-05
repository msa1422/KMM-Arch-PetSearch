@file:Suppress("DSL_SCOPE_VIOLATION")

import com.msa.petsearch.PackageNameAccessor.SHARED_CORE_ENTITY
import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = SHARED_CORE_ENTITY
}

dependencies {
    commonMainImplementation(libs.kotlinx.serialization)
}
