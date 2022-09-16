package com.msa.petsearch.shared.datainfrastructurenetwork.mapper.response

import com.msa.petsearch.shared.coreentity.response.PaginationInfo
import com.msa.petsearch.shared.datainfrastructurenetwork.response.PaginationInfoDTO

internal fun PaginationInfoDTO.toDomainEntity() =
    PaginationInfo(countPerPage, totalCount, currentPage, totalPages)
