package com.msa.petsearch.shared.ui.home.testdoubles

import com.msa.petsearch.shared.core.entity.PetType
import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.core.entity.petinfo.enum.PetAge
import com.msa.petsearch.shared.core.entity.petinfo.enum.PetCoat
import com.msa.petsearch.shared.core.entity.petinfo.enum.PetGender
import com.msa.petsearch.shared.core.entity.petinfo.enum.PetSize
import com.msa.petsearch.shared.core.entity.petinfo.enum.PetStatus
import com.msa.petsearch.shared.core.entity.response.PaginationInfo
import com.msa.petsearch.shared.core.entity.response.PetTypesResponse
import com.msa.petsearch.shared.core.entity.response.SearchPetResponse

internal object TestFake {

    val petTypesResponse = PetTypesResponse(
        types = listOf(
            PetType(
                name = "Dog",
                coats = listOf("DogCoat0", "DogCoat1", "DogCoat2", "DogCoat3"),
                colors = listOf(
                    "DogColor0",
                    "DogColor1",
                    "DogColor2",
                    "DogColor3",
                    "DogColor4"
                ),
                genders = listOf("Male", "Female")
            ),
            PetType(
                name = "Cat",
                coats = listOf("CatCoat0", "CatCoat1", "CatCoat2", "CatCoat3"),
                colors = listOf(
                    "CatColor0",
                    "CatColor1",
                    "CatColor2",
                    "CatColor3",
                    "CatColor4"
                ),
                genders = listOf("Male", "Female")
            ),
            PetType(
                name = "Horse",
                coats = listOf("HorseCoat0", "HorseCoat1", "HorseCoat2", "HorseCoat3"),
                colors = listOf(
                    "HorseColor0",
                    "HorseColor1",
                    "HorseColor2",
                    "HorseColor3",
                    "HorseColor4"
                ),
                genders = listOf("Male", "Female")
            )
        )
    )

    val petDogSearchResponse = SearchPetResponse(
        animals = listOf(
            PetInfo(
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
        ),
        pagination = PaginationInfo(
            countPerPage = 20,
            totalCount = 100,
            currentPage = 1,
            totalPages = 5
        )
    )

    val petCatSearchResponse = SearchPetResponse(
        animals = listOf(
            PetInfo(
                id = 1,
                organizationId = "0",
                url = "someUrl",
                type = "Cat",
                name = "Smelly Cat",
                species = "Domestic short hair",
                breeds = null,
                colors = null,
                age = PetAge.BABY,
                gender = PetGender.FEMALE,
                size = PetSize.SMALL,
                coat = PetCoat.SHORT,
                description = "Smelly Cat, Smelly Cat, What are they feeding you?",
                shortDescription = "Fat cat, Smelly Cat",
                photos = listOf(),
                videos = listOf(),
                status = PetStatus.ADOPTABLE,
                attributes = null,
                environment = null,
                tags = listOf("Smelly", "Fat"),
                contact = null,
                publishedAt = "Sometime in 1994",
                distance = 1.0
            )
        ),
        pagination = PaginationInfo(
            countPerPage = 20,
            totalCount = 100,
            currentPage = 1,
            totalPages = 5
        )
    )
}
