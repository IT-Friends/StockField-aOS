package com.evangers.stockfield.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FundEntity(
    @Json(name = "fund_name") val fundName: String = "",
    @Json(name = "fundHoldings") val stockList: List<StockEntity>
)