package com.msa.petsearch.shared.coreentity

data class PetType(
    val name: String,
    val coats: List<String>,
    val colors: List<String>,
    val genders: List<String>
)
