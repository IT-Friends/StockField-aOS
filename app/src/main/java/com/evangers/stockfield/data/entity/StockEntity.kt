package com.evangers.stockfield.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StockEntity(
    @Json(name = "country") val country: String?,
    @Json(name = "displayName") val displayName: String?,
    @Json(name = "fiftyTwoWeekHigh") val fiftyTwoWeekHigh: Float,
    @Json(name = "fiftyTwoWeekLow") val fiftyTwoWeekLow: Float,
    @Json(name = "industry") val industry: String?,
    @Json(name = "market") val market: String?,
    @Json(name = "marketCap") val marketCap: Float?,
    @Json(name = "name") val name: String,
    @Json(name = "priceEarningsRatio") val priceEarningsRatio: Float?,
    @Json(name = "priceToBookRatio") val priceToBookRatio: Float?,
    @Json(name = "regularMarketChange") val regularMarketChange: Float?,
    @Json(name = "regularMarketChangePercent") val regularMarketChangePercent: Float?,
    @Json(name = "regularMarketDayHigh") val regularMarketDayHigh: Float?,
    @Json(name = "regularMarketDayLow") val regularMarketDayLow: Float?,
    @Json(name = "regularMarketPreviousClose") val regularMarketPreviousClose: Float?,
    @Json(name = "regularMarketPrice") val regularMarketPrice: Float?,
    @Json(name = "regularMarketTime") val regularMarketTime: String?,
    @Json(name = "regularMarketVolume") val regularMarketVolume: Float?,
    @Json(name = "returnOnEquity") val returnOnEquity: Float?,
    @Json(name = "sector") val sector: String?,
    @Json(name = "ticker") val ticker: String
)
