package com.evangers.stockfield.domain.model

data class StockModel(
    val country: String,
    val displayName: String,
    val fiftyTwoWeekHigh: Float,
    val fiftyTwoWeekLow: Float,
    val industry: String,
    val market: String?,
    val marketCap: Float,
    val name: String,
    val priceEarningsRatio: Float,
    val priceToBookRatio: Float,
    val regularMarketChange: Float,
    val regularMarketChangePercent: Float,
    val regularMarketDayHigh: Float,
    val regularMarketDayLow: Float,
    val regularMarketPreviousClose: Float,
    val regularMarketPrice: Float,
    val regularMarketTime: String,
    val regularMarketVolume: Float,
    val returnOnEquity: Float,
    val sector: String,
    val ticker: String
)
