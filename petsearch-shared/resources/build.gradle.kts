@file:Suppress("DSL_SCOPE_VIOLATION")

import com.msa.petsearch.SHARED_PACKAGE
import com.msa.petsearch.join
import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.moko.resources)
}

android {
    namespace = SHARED_PACKAGE.join(projects.petsearchShared.resources)
}

dependencies {
    commonMainApi(libs.moko.resources)
    androidMainApi(libs.moko.resources.compose)
}

multiplatformResources {
    multiplatformResourcesPackage = SHARED_PACKAGE.join(projects.petsearchShared.resources)
    multiplatformResourcesClassName = "SharedR"
    disableStaticFrameworkWarning = true
}
