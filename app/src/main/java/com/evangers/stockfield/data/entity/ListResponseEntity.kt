package com.evangers.stockfield.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListResponseEntity<T>(
    @Json(name = "totalCounts") val totalCounts: Int,
    @Json(name = "list") val list: List<T>
)
