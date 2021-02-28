package com.evangers.stockfield.domain.model

data class FundHoldingsModel(
    val totalCounts: Int,
    val fundHoldingList: List<FundHoldingModel>
)