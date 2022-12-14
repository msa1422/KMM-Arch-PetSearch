import com.msa.petsearch.ANDROID_PACKAGE
import com.msa.petsearch.join
import com.msa.petsearch.util.libs

plugins {
    `android-app-plugin`
}

android {
    namespace = ANDROID_PACKAGE.join(projects.petsearchAndroid.app)
}

dependencies {
    implementation(projects.petsearchAndroid.activity)
    implementation(projects.petsearchShared.shared)
    implementation(libs.koin.core)
    implementation(libs.coil.compose)
}
