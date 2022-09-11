package com.petsapp.petfinder.shared.data_infrastructure_network.mapper.response

import com.petsapp.petfinder.shared.core_entity.response.PaginationInfo
import com.petsapp.petfinder.shared.data_infrastructure_network.response.PaginationInfoDTO

internal fun PaginationInfoDTO.toDomainEntity() =
    PaginationInfo(countPerPage, totalCount, currentPage, totalPages)
