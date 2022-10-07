package com.msa.petsearch.shared.repository.home.mapper.network.response

import com.msa.petsearch.shared.coreentity.response.PaginationInfo
import com.msa.petsearch.shared.networkinfra.response.PaginationInfoDTO

internal fun PaginationInfoDTO.toDomainEntity() =
    PaginationInfo(countPerPage, totalCount, currentPage, totalPages)
