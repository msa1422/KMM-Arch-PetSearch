package com.msa.petsearch.shared.networkinfra.testfake

import com.msa.petsearch.shared.networkinfra.dto.PetTypeDTO
import com.msa.petsearch.shared.networkinfra.response.PetTypesResponseDTO

internal val FakePetTypesResponseDTO = PetTypesResponseDTO(
    types = listOf(
        PetTypeDTO(
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
        PetTypeDTO(
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
        PetTypeDTO(
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
