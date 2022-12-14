package com.msa.petsearch.shared.data.infra.cache.entity

internal class PetInfoRealmObject(
    // @PrimaryKey
    var id: Long? = null,
    var name: String? = null,
    var type: String? = null,
    var description: String? = null,
    var shortDescription: String? = null
) /*: RealmObject {
    @Suppress("UNUSED")
    constructor() : this(null)
}*/
