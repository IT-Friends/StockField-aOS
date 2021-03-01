package com.evangers.stockfield.domain.model

data class ListResponseModel<T>(
    val totalCounts: Int,
    val list: List<T>
)
