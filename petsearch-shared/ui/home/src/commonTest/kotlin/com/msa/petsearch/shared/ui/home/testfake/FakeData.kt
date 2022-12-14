package com.msa.petsearch.shared.ui.home.testfake

import com.kuuurt.paging.multiplatform.PagingData
import com.msa.petsearch.shared.core.entity.PetSearchParams
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
import com.msa.petsearch.shared.core.util.commonflow.asCommonFlow
import com.msa.petsearch.shared.ui.home.contract.store.HomeState
import kotlinx.coroutines.flow.emptyFlow

internal object FakeData {

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

    val petSearchResponse = SearchPetResponse(
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

    val searchParams = PetSearchParams(
        breed = listOf("Breed0", "Breed2"),
        size = listOf(PetSize.SMALL, PetSize.MEDIUM),
        gender = listOf(PetGender.MALE, PetGender.MALE),
        age = listOf(PetAge.ADULT),
        color = listOf("PetColor0", "PetColor1"),
        coat = listOf(PetCoat.CURLY, PetCoat.SHORT),
        status = listOf(PetStatus.ADOPTABLE),
        goodWithChildren = false,
        goodWithDogs = true,
        goodWithCats = false,
        houseTrained = false,
        declawed = false,
        specialNeeds = false
    )

    private const val petSearchResponseErrorMessage = "Some error occurred while fetching Pets"
    val petSearchResponseError = Throwable(petSearchResponseErrorMessage)

    const val petTypeResponseErrorMessage = "Some error occurred while fetching Pet types"
    val petTypeResponseError = Throwable(petTypeResponseErrorMessage)

    val fakePagingData = emptyFlow<PagingData<PetInfo>>().asCommonFlow()

    fun getHomeState() = HomeState(
        petTypesResponse = petTypesResponse,
        searchParams = searchParams
    )
}
