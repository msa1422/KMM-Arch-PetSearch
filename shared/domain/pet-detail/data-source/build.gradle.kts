import com.petsapp.petfinder.constants.SharedModule

plugins {
    `kmm-shared-module-plugin`
}

android {
    namespace = SharedModule.Domain.PetDetail.DataSource.PACKAGE
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}

dependencies {
    commonMainImplementation(project(SharedModule.DomainCore.Entity.MODULE))
    commonMainImplementation(project(SharedModule.DomainCore.Util.MODULE))
}