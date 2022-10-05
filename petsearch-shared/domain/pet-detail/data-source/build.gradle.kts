import com.msa.petsearch.PackageNameAccessor.DATASOURCE_PET_DETAIL

plugins {
    `kmm-shared-module-plugin`
}

android {
    namespace = DATASOURCE_PET_DETAIL
}

dependencies {
    commonMainImplementation(projects.petsearchShared.core.entity)
    commonMainImplementation(projects.petsearchShared.core.util)
}
