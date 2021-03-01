package com.evangers.stockfield.domain.repository

import com.evangers.stockfield.domain.model.ListResponseModel
import com.evangers.stockfield.domain.model.StockModel

interface StockRepository {
    suspend fun getStock(ticker: String): StockModel
    suspend fun getStockSearchResult(page: Int, keyword: String): ListResponseModel<StockModel>
}