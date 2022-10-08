import com.msa.petsearch.PackageNameAccessor.SHARED_CORE_TEST
import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
}

android {
    namespace = SHARED_CORE_TEST
}

dependencies {
    commonMainImplementation(libs.kotlinx.coroutines.test)
    commonMainImplementation(libs.testing.kotest.framework.engine)
}
