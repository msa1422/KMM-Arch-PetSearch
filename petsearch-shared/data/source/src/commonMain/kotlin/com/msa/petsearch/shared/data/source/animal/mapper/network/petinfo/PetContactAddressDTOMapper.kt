package com.msa.petsearch.shared.data.source.animal.mapper.network.petinfo

import com.msa.petsearch.shared.core.entity.petinfo.PetContactAddress
import com.msa.petsearch.shared.data.source.animal.model.network.petinfo.PetContactAddressDTO

internal fun PetContactAddressDTO.toDomainEntity() =
    PetContactAddress(
        address1 = address1 ?: "",
        address2 = address2 ?: "",
        city = city ?: "",
        state = state ?: "",
        postcode = postcode ?: "",
        country = country ?: ""
    )
