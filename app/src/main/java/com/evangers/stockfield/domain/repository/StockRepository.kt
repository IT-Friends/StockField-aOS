package com.evangers.stockfield.domain.repository

import com.evangers.stockfield.domain.model.ListResponseModel
import com.evangers.stockfield.domain.model.StockModel
import com.evangers.stockfield.domain.model.StockPriceModel

interface StockRepository {
    suspend fun getStock(
        ticker: String
    ): StockModel

    suspend fun getStockSearchResult(
        page: Int, keyword: String
    ): ListResponseModel<StockModel>

    suspend fun getHistoryFromStock(
        ticker: String,
        page: Int,
        perPage: Int?,
        order: String
    ): ListResponseModel<StockPriceModel>
}