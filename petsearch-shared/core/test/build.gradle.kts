import com.msa.petsearch.SHARED_PACKAGE
import com.msa.petsearch.join
import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
}

android {
    namespace = SHARED_PACKAGE.join(
        projects.petsearchShared.core,
        projects.petsearchShared.core.test
    )
}

dependencies {
    commonMainImplementation(libs.kotlinx.coroutines.test)
    commonMainImplementation(libs.testing.kotest.framework.engine)
}
