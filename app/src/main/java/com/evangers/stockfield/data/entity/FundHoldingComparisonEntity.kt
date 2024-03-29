package com.evangers.stockfield.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FundHoldingComparisonEntity(
    @Json(name = "displayName") val displayName: String?,
    @Json(name = "rank") val rank: Int,
    @Json(name = "rankDifference") val rankDifference: Int?,
    @Json(name = "ticker") val ticker: String,
    @Json(name = "shares") val shares: Double,
    @Json(name = "sharesDifference") val sharesDifference: Double?,
    @Json(name = "weight") val weight: Float,
    @Json(name = "weightDifference") val weightDifference: Float?,
    @Json(name = "closingPrice") val closingPrice: Float?,
    @Json(name = "closingPriceChangePercent") val closingPriceChangePercent: Float?,
    @Json(name = "closingPriceDifference") val closingPriceDifference: Float?,
    @Json(name = "dateTo") val dateTo: String?,
    @Json(name = "dateFrom") val dateFrom: String?
)