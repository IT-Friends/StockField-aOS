package com.evangers.stockfield.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CompanyEntity(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String
)