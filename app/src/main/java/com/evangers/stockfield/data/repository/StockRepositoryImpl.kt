package com.evangers.stockfield.data.repository

import com.evangers.stockfield.data.api.StockFieldApi
import com.evangers.stockfield.domain.model.ListResponseModel
import com.evangers.stockfield.domain.model.StockModel
import com.evangers.stockfield.domain.model.StockPriceModel
import com.evangers.stockfield.domain.repository.StockRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val stockFieldApi: StockFieldApi
) : StockRepository {
    override suspend fun getStock(ticker: String): StockModel {
        val response = withContext(Dispatchers.IO) {
            stockFieldApi.getStock(ticker)
        }
        return StockModel(
            country = response.country,
            displayName = response.displayName,
            fiftyTwoWeekHigh = response.fiftyTwoWeekHigh,
            fiftyTwoWeekLow = response.fiftyTwoWeekLow,
            industry = response.industry,
            market = response.market,
            marketCap = response.marketCap,
            name = response.name,
            priceEarningsRatio = response.priceEarningsRatio,
            priceToBookRatio = response.priceToBookRatio,
            regularMarketChange = response.regularMarketChange,
            regularMarketChangePercent = response.regularMarketChangePercent,
            regularMarketDayHigh = response.regularMarketDayHigh,
            regularMarketDayLow = response.regularMarketDayLow,
            regularMarketPreviousClose = response.regularMarketPreviousClose,
            regularMarketPrice = response.regularMarketPrice,
            regularMarketTime = response.regularMarketTime ?: "",
            regularMarketVolume = response.regularMarketVolume,
            returnOnEquity = response.returnOnEquity,
            sector = response.sector,
            ticker = response.ticker
        )
    }

    override suspend fun getStockSearchResult(
        page: Int,
        keyword: String
    ): ListResponseModel<StockModel> {
        throw Exception("Not Ready")
    }

    override suspend fun getHistoryFromStock(
        ticker: String,
        page: Int,
        perPage: Int?,
        order: String
    ): ListResponseModel<StockPriceModel> {
        return withContext(Dispatchers.IO) {
            val response = stockFieldApi.getHistoryFromStock(
                ticker = ticker,
                perPage = perPage,
                page = page,
                order = order
            )

            ListResponseModel(
                totalCounts = response.totalCounts,
                list = response.list.map {
                    StockPriceModel(
                        date = it.date,
                        openPrice = it.openPrice,
                        closePrice = it.closePrice,
                        highPrice = it.highPrice,
                        lowPrice = it.lowPrice,
                        volume = it.volume
                    )
                }
            )
        }

    }

}