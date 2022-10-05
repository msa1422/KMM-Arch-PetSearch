package com.msa.petsearch.shared.cacheinfra.realm

import com.msa.petsearch.shared.cacheinfra.entity.PetInfoRealObject
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

private const val REALM_DB_NAME = "THE_REALM_OF_PETS"

internal val RealmDb: Realm
    get() {
        return Realm.open(
            RealmConfiguration
                .Builder(setOf(PetInfoRealObject::class))
                .name(REALM_DB_NAME)
                .schemaVersion(0)
                .build()
        )
    }
