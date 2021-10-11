package com.evangers.stockfield.domain.model

data class FundHoldingComparisonModel(
    val displayName: String,
    val rank: Int,
    val rankDifference: Int?,
    val ticker: String,
    val shares: Double,
    val sharesDifference: Double?,
    val weight: Float,
    val weightDifference: Float?,
    val closingPrice: Float?,
    val closingPriceChangePercent: Float?,
    val closingPriceDifference: Float?,
    val dateTo: String?,
    val dateFrom: String?
)