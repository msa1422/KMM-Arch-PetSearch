import com.msa.petsearch.ANDROID_PACKAGE
import com.msa.petsearch.join
import com.msa.petsearch.util.libs

plugins {
    `android-ui-plugin`
}

android {
    namespace = ANDROID_PACKAGE.join(projects.petsearchAndroid.activity)
}

dependencies {
    api(projects.petsearchAndroid.common.components)
    api(projects.petsearchAndroid.common.compose)
    api(projects.petsearchAndroid.features.home)
    api(projects.petsearchAndroid.features.petdetail)

    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.core)
    implementation(projects.petsearchShared.core.util)
    implementation(projects.petsearchShared.resources)
}
