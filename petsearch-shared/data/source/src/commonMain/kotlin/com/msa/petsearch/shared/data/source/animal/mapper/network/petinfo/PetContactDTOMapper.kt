package com.msa.petsearch.shared.data.source.animal.mapper.network.petinfo

import com.msa.petsearch.shared.core.entity.petinfo.PetContact
import com.msa.petsearch.shared.data.source.animal.model.network.petinfo.PetContactDTO

internal fun PetContactDTO.toDomainEntity() =
    PetContact(
        email = email ?: "",
        phone = phone ?: "",
        address = address?.toDomainEntity()
    )
