package com.petsapp.petfinder.shared.datainfrastructurenetwork.mapper.pet_info

import com.petsapp.petfinder.shared.coreentity.petinfo.PetContact
import com.petsapp.petfinder.shared.datainfrastructurenetwork.dto.pet_info.PetContactDTO

internal fun PetContactDTO.toDomainEntity() =
    PetContact(
        email = email ?: "",
        phone = phone ?: "",
        address = address?.toDomainEntity()
    )
