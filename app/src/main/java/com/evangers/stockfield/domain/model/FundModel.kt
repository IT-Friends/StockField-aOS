package com.evangers.stockfield.domain.model

data class FundModel(
    val fundName: String,
    val stockList: List<StockModel>
)