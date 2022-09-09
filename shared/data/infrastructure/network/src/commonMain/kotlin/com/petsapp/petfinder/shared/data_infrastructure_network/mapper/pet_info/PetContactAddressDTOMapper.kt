package com.petsapp.petfinder.shared.data_infrastructure_network.mapper.pet_info

import com.petsapp.petfinder.shared.core_entity.pet_info.PetContactAddress
import com.petsapp.petfinder.shared.data_infrastructure_network.dto.pet_info.PetContactAddressDTO

internal fun PetContactAddressDTO.toDomainEntity() =
    PetContactAddress(
        address1 = address1 ?: "",
        address2 = address2 ?: "",
        city = city ?: "",
        state = state ?: "",
        postcode = postcode ?: "",
        country = country ?: ""
    )