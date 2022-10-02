import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
}

dependencies {
    commonMainImplementation(libs.kotlinx.coroutines.core)
}
