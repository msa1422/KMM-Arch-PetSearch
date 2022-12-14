package com.msa.petsearch.shared.core.entity.response

data class PaginationInfo(
    val countPerPage: Int,
    val totalCount: Int,
    val currentPage: Int,
    val totalPages: Int
)
