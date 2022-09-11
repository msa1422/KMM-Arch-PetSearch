package com.petsapp.petfinder.shared.datainfrastructurenetwork.mapper.pet_info

import com.petsapp.petfinder.shared.coreentity.petinfo.PetContactAddress
import com.petsapp.petfinder.shared.datainfrastructurenetwork.dto.pet_info.PetContactAddressDTO

internal fun PetContactAddressDTO.toDomainEntity() =
    PetContactAddress(
        address1 = address1 ?: "",
        address2 = address2 ?: "",
        city = city ?: "",
        state = state ?: "",
        postcode = postcode ?: "",
        country = country ?: ""
    )
