package com.evangers.stockfield.domain.model

data class HistoryModel(
    val date: String,
    val shares: Float,
    val sharesDifference: Float,
    val weight: Float,
    val weightDifference: Float
)
