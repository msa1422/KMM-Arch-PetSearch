package com.msa.petsearch.shared.ui.petdetail.testfake

import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.core.entity.petinfo.enum.PetAge
import com.msa.petsearch.shared.core.entity.petinfo.enum.PetCoat
import com.msa.petsearch.shared.core.entity.petinfo.enum.PetGender
import com.msa.petsearch.shared.core.entity.petinfo.enum.PetSize
import com.msa.petsearch.shared.core.entity.petinfo.enum.PetStatus
import com.msa.petsearch.shared.core.util.extension.encodeToString
import com.msa.petsearch.shared.core.util.sharedviewmodel.navigation.ARG_PET_INFO
import com.msa.petsearch.shared.ui.petdetail.contract.store.PetDetailState

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
        publishedAt = "Somewhere around midnight",
        distance = 1.0
    )

    val argsMap = hashMapOf(Pair(ARG_PET_INFO, petInfo.encodeToString()))

    val petDetailState = PetDetailState()
}
