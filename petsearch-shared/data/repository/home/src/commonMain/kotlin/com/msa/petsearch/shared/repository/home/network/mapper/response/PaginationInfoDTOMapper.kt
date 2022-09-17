package com.msa.petsearch.shared.repository.home.network.mapper.response

import com.msa.petsearch.shared.coreentity.response.PaginationInfo
import com.msa.petsearch.shared.repository.home.network.response.PaginationInfoDTO

internal fun PaginationInfoDTO.toDomainEntity() =
    PaginationInfo(countPerPage, totalCount, currentPage, totalPages)
