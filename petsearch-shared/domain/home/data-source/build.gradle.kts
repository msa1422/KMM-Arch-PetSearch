import com.msa.petsearch.PackageNameAccessor.DATASOURCE_HOME

plugins {
    `kmm-shared-module-plugin`
}

android {
    namespace = DATASOURCE_HOME
}

dependencies {
    commonMainImplementation(projects.petsearchShared.core.entity)
    commonMainImplementation(projects.petsearchShared.core.util)
}
