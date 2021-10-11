package com.evangers.stockfield.domain.model

data class FundModel(
    val id: Int,
    val name: String,
    val companyIconUrl: String?,
    val companyName: String?,
    val fundHoldingComparisons: List<FundHoldingComparisonModel>?
)
