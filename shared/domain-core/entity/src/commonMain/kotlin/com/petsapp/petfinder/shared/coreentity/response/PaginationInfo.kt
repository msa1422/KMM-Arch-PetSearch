package com.petsapp.petfinder.shared.coreentity.response

data class PaginationInfo(
    val countPerPage: Int,
    val totalCount: Int,
    val currentPage: Int,
    val totalPages: Int
)
