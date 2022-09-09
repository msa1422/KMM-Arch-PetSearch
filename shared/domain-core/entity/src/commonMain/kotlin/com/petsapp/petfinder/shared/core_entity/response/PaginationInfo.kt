package com.petsapp.petfinder.shared.core_entity.response

data class PaginationInfo(
    val countPerPage: Int,
    val totalCount: Int,
    val currentPage: Int,
    val totalPages: Int
)
