package com.msa.petsearch.shared.data.source.testfake

import com.msa.petsearch.shared.data.source.animal.model.network.petinfo.PetInfoDTO
import com.msa.petsearch.shared.data.source.animal.model.network.response.PaginationInfoDTO
import com.msa.petsearch.shared.data.source.animal.model.network.response.SearchPetResponseDTO

internal val FakePetSearchResponseDTO = SearchPetResponseDTO(
    animals = listOf(
        PetInfoDTO(
            id = 0,
            organizationId = "0",
            url = "someUrl",
            type = "Dog",
            species = "Werewolf",
            breeds = null,
            colors = null,
            age = "adult",
            gender = "male",
            size = "medium",
            coat = "short",
            name = "Dracula",
            description = "Wanders at night in search of prey, Beware!",
            photos = listOf(),
            videos = listOf(),
            status = "found",
            attributes = null,
            environment = null,
            tags = listOf("Dangerous", "Fast"),
            contact = null,
            publishedAt = "Somewhere around midnight",
            distance = 1.0
        )
    ),
    pagination = PaginationInfoDTO(
        countPerPage = 20,
        totalCount = 100,
        currentPage = 1,
        totalPages = 5
    )
)
