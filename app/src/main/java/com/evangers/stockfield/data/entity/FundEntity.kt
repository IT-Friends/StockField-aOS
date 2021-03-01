package com.evangers.stockfield.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FundEntity(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "companyIconUrl") val companyIconUrl: String?,
    @Json(name = "companyName") val companyName: String?,
    @Json(name = "fundHoldingComparisons") val fundHoldingComparisons: List<FundHoldingComparisonEntity>?
)
