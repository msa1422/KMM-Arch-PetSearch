@file:Suppress("DSL_SCOPE_VIOLATION")

import com.msa.petsearch.util.libs
import com.msa.petsearch.PackageNameAccessor.SHARED_RESOURCES_PACKAGE

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.moko.resources)
}

android {
    sourceSets["main"].apply {
        assets.srcDir(File(buildDir, "generated/moko/androidMain/assets"))
        res.srcDir(File(buildDir, "generated/moko/androidMain/res"))
    }
}

dependencies {
    commonMainApi(libs.moko.resources)
    androidMainApi(libs.moko.resources.compose)
}

multiplatformResources {
    multiplatformResourcesPackage = SHARED_RESOURCES_PACKAGE
    multiplatformResourcesClassName = "SharedR"
}
