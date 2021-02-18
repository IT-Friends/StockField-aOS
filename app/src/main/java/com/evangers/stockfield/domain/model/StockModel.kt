package com.evangers.stockfield.domain.model

data class StockModel(
    val rank: Int,
    val rankDifference: Int?,
    val ticker: String,
    val shares: Int,
    val sharesDifference: Int?,
    val weight: Float,
    val weightDifference: Float?,
    val closingPrice: Float?,
    val closingPriceDifference: Float?,
    val dateTo: String?,
    val dateFrom: String?
)