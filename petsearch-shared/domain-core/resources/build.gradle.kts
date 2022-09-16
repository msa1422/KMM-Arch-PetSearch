import com.msa.petsearch.util.libs
import com.msa.petsearch.PackageNameAccessor.CORE_RESOURCES_PACKAGE

plugins {
    `kmm-shared-module-plugin`
    id("dev.icerock.mobile.multiplatform-resources")
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
    multiplatformResourcesPackage = CORE_RESOURCES_PACKAGE
    multiplatformResourcesClassName = "CoreR"
}
