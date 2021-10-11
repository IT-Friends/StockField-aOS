package com.evangers.stockfield.domain.model

data class HistoryModel(
    val date: String,
    val shares: Double,
    val sharesDifference: Double?,
    val weight: Float,
    val weightDifference: Float?
)
