package com.msa.petsearch.shared.cacheinfra.entity

internal class PetInfoRealmObject(
    // @PrimaryKey
    var id: Long? = null,
    var name: String? = null,
    var type: String? = null,
    var description: String? = null,
    var shortDescription: String? = null

    // TODO: Add Other fields and respective Entities and Mappers

) /*: RealmObject {
    @Suppress("UNUSED")
    constructor() : this(null)
}*/
