package com.msa.petsearch.shared.core.entity

data class PetType(
    val name: String,
    val coats: List<String>,
    val colors: List<String>,
    val genders: List<String>
)
