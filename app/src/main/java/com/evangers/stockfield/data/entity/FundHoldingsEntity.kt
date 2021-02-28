package com.evangers.stockfield.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FundHoldingsEntity(
    @Json(name = "totalCounts") val totalCounts: Int,
    @Json(name = "fundHoldings") val fundHoldingList: List<FundHoldingEntity>
)