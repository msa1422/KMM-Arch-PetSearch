package com.msa.petsearch.shared.datainfrastructurenetwork.mapper.response

import com.petsapp.petfinder.shared.coreentity.response.PaginationInfo
import com.petsapp.petfinder.shared.datainfrastructurenetwork.response.PaginationInfoDTO

internal fun PaginationInfoDTO.toDomainEntity() =
    PaginationInfo(countPerPage, totalCount, currentPage, totalPages)
