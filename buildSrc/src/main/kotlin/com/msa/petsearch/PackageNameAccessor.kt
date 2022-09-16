package com.msa.petsearch

object PackageNameAccessor {
    val DATA_INFRASTRUCTURE_NETWORK_PACKAGE = "${this.javaClass.packageName}.shared.datainfrastructurenetwork"
    val CORE_RESOURCES_PACKAGE = "${this.javaClass.packageName}.shared.coreres"
    val SHARED_PACKAGE = "${this.javaClass.packageName}.shared"
}
