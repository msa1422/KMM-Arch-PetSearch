import com.petsapp.petfinder.constants.SharedModule
import com.petsapp.petfinder.util.libs

plugins {
    `kmm-shared-module-plugin`
    id("dev.icerock.mobile.multiplatform-resources")
}

android {
    namespace = SharedModule.DomainCore.Resources.PACKAGE
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

dependencies {
    commonMainApi(libs.moko.resources)
    androidMainApi(libs.moko.resources.compose)
}

multiplatformResources {
    multiplatformResourcesPackage = SharedModule.DomainCore.Resources.PACKAGE
    multiplatformResourcesClassName = "CoreR" // optional, default MR
}