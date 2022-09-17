package com.msa.petsearch.shared.repository.home.network.mapper.pet_info

import com.msa.petsearch.shared.coreentity.petinfo.PetContactAddress
import com.msa.petsearch.shared.repository.home.network.dto.pet_info.PetContactAddressDTO

internal fun PetContactAddressDTO.toDomainEntity() =
    PetContactAddress(
        address1 = address1 ?: "",
        address2 = address2 ?: "",
        city = city ?: "",
        state = state ?: "",
        postcode = postcode ?: "",
        country = country ?: ""
    )
