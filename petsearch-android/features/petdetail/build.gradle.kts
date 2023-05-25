import com.msa.petsearch.ANDROID_PACKAGE
import com.msa.petsearch.join
import com.msa.petsearch.util.libs

plugins {
    `android-ui-plugin`
}

android {
    namespace = ANDROID_PACKAGE.join(
        projects.petsearchAndroid.features,
        projects.petsearchAndroid.features.petdetail
    )
}

dependencies {
    implementation(libs.accompanist.pager)
    implementation(libs.androidx.compose.material)
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.core)

    implementation(projects.petsearchAndroid.common.compose)
    implementation(projects.petsearchAndroid.common.components)
    implementation(projects.petsearchAndroid.common.compose)
    implementation(projects.petsearchShared.core.entity)
    implementation(projects.petsearchShared.core.util)
    implementation(projects.petsearchShared.resources)

    implementation(projects.petsearchShared.ui.petdetail)
}
