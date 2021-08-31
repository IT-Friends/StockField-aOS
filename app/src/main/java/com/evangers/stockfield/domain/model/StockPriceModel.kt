package com.evangers.stockfield.domain.model

data class StockPriceModel(
    val date: String,
    val openPrice: String,
    val closePrice: Double,
    val highPrice: Double,
    val lowPrice: Double,
    val volume: Double
)