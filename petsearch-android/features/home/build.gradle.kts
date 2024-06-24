import com.msa.petsearch.ANDROID_PACKAGE
import com.msa.petsearch.join
import com.msa.petsearch.util.libs

plugins {
    `android-ui-plugin`
}

android {
    namespace = ANDROID_PACKAGE.join(
        projects.petsearchAndroid.features,
        projects.petsearchAndroid.features.home
    )
}

dependencies {
    implementation(platform(libs.koin.bom))
    implementation(libs.kmp.observableviewmodel)
    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)
    implementation(libs.androidx.compose.paging)
    // implementation(libs.androidx.compose.paging.runtime)
    implementation(libs.kuuuurt.multiplatform.paging)

    implementation(projects.petsearchAndroid.common.compose)
    implementation(projects.petsearchAndroid.common.components)

    implementation(projects.petsearchShared.core.entity)
    implementation(projects.petsearchShared.core.util)
    implementation(projects.petsearchShared.resources)

    implementation(projects.petsearchShared.ui.home)
}
