package com.petsapp.petfinder.shared.data_infrastructure_network.mapper.pet_info

import com.petsapp.petfinder.shared.core_entity.pet_info.PetContact
import com.petsapp.petfinder.shared.data_infrastructure_network.dto.pet_info.PetContactDTO

internal fun PetContactDTO.toDomainEntity() =
    PetContact(
        email = email ?: "",
        phone = phone ?: "",
        address = address?.toDomainEntity()
    )