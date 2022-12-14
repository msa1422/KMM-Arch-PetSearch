import com.msa.petsearch.SHARED_PACKAGE
import com.msa.petsearch.join
import com.msa.petsearch.util.libs

plugins {
    `kmm-shared-module-plugin`
}

android {
    namespace = SHARED_PACKAGE.join(
        projects.petsearchShared.domain,
        projects.petsearchShared.domain.petdetail
    )
}

dependencies {
    commonMainImplementation(libs.kotlinx.coroutines.core)
    commonMainImplementation(libs.koin.core)

    commonMainImplementation(projects.petsearchShared.core.entity)
    commonMainImplementation(projects.petsearchShared.core.util)

    // Data dependency here for Dependency Inversion
    commonMainImplementation(projects.petsearchShared.data.repository)
}
