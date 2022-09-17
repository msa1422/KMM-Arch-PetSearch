package com.msa.petsearch.shared.repository.home.network.mapper.pet_info

import com.msa.petsearch.shared.coreentity.petinfo.PetContact
import com.msa.petsearch.shared.repository.home.network.dto.pet_info.PetContactDTO

internal fun PetContactDTO.toDomainEntity() =
    PetContact(
        email = email ?: "",
        phone = phone ?: "",
        address = address?.toDomainEntity()
    )
