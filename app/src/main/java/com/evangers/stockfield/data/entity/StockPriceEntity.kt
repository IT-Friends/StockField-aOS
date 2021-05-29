package com.evangers.stockfield.data.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StockPriceEntity(
    val date: String,
    val openPrice: String,
    val closePrice: Double,
    val highPrice: Double,
    val lowPrice: Double,
    val volume: Double
)