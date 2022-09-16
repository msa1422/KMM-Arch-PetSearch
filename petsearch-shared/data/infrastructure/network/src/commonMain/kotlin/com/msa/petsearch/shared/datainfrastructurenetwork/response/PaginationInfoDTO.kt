package com.msa.petsearch.shared.datainfrastructurenetwork.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PaginationInfoDTO(
    @SerialName("count_per_page") val countPerPage: Int,
    @SerialName("total_count") val totalCount: Int,
    @SerialName("current_page") val currentPage: Int,
    @SerialName("total_pages") val totalPages: Int
)
