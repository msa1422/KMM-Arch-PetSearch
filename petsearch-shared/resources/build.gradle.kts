@file:Suppress("DSL_SCOPE_VIOLATION")

import com.msa.petsearch.SHARED_PACKAGE
import com.msa.petsearch.join
import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.moko.resources)
}

android {
    namespace = NAMESPACE
}

dependencies {
    commonMainApi(libs.moko.resources)
    androidMainApi(libs.moko.resources.compose)
}

multiplatformResources {
    resourcesPackage.set(NAMESPACE)
    resourcesClassName.set("SharedR")
}

@Suppress("PrivatePropertyName")
private val NAMESPACE: String
    get() = SHARED_PACKAGE.join(projects.petsearchShared.resources)
