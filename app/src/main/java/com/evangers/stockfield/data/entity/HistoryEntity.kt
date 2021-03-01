package com.evangers.stockfield.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HistoryEntity(
    @Json(name = "date") val date: String,
    @Json(name = "shares") val shares: Float,
    @Json(name = "sharesDifference") val sharesDifference: Float,
    @Json(name = "weight") val weight: Float,
    @Json(name = "weightDifference") val weightDifference: Float
)
