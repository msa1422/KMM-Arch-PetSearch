@file:Suppress("DSL_SCOPE_VIOLATION")

import com.msa.petsearch.SHARED_PACKAGE
import com.msa.petsearch.join
import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = SHARED_PACKAGE.join(
        projects.petsearchShared.core,
        projects.petsearchShared.core.entity
    )
}

dependencies {
    commonMainImplementation(libs.kotlinx.serialization)
}
