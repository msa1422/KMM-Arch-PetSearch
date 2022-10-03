package com.msa.petsearch.shared.domain.petdetailuicontract.testfake

import com.msa.petsearch.shared.coreentity.petinfo.PetInfo
import com.msa.petsearch.shared.coreentity.petinfo.enum.*
import com.msa.petsearch.shared.coreutil.extension.encodeToString
import com.msa.petsearch.shared.coreutil.sharedviewmodel.navigation.ARG_PET_INFO
import com.msa.petsearch.shared.domain.petdetailuicontract.contract.store.PetDetailState

internal object FakeData {

    val petInfo = PetInfo(
        id = 0,
        organizationId = "0",
        url = "someUrl",
        type = "Dog",
        species = "Werewolf",
        breeds = null,
        colors = null,
        age = PetAge.ADULT,
        gender = PetGender.MALE,
        size = PetSize.MEDIUM,
        coat = PetCoat.SHORT,
        name = "Dracula",
        description = "Wanders at night in search of prey, Beware!",
        shortDescription = "Adult Male, Werewolf",
        photos = listOf(),
        videos = listOf(),
        status = PetStatus.FOUND,
        attributes = null,
        environment = null,
        tags = listOf("Dangerous", "Fast"),
        contact = null,
        published_at = "Somewhere around midnight",
        distance = 1.0
    )

    val argsMap = hashMapOf(Pair(ARG_PET_INFO, petInfo.encodeToString()))

    val petDetailState = PetDetailState()
}
