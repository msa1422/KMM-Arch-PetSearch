package com.msa.petsearch.shared.data.source.animal.mapper.network.response

import com.msa.petsearch.shared.core.entity.response.PaginationInfo
import com.msa.petsearch.shared.data.source.animal.model.network.response.PaginationInfoDTO

internal fun PaginationInfoDTO.toDomainEntity() =
    PaginationInfo(countPerPage, totalCount, currentPage, totalPages)
