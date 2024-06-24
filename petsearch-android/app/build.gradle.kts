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
    implementation(platform(libs.koin.bom))
    implementation(libs.coil.compose)
    implementation(libs.koin.core)
    implementation(libs.koin.core.coroutines)
    implementation(projects.petsearchAndroid.activity)
    implementation(projects.petsearchShared.shared)
}
