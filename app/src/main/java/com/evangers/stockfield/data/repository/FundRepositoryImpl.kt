package com.evangers.stockfield.data.repository

import com.evangers.stockfield.data.api.StockFieldApi
import com.evangers.stockfield.data.mapper.FundHoldingsMapper
import com.evangers.stockfield.data.mapper.FundMapper
import com.evangers.stockfield.data.mapper.HistoryMapper
import com.evangers.stockfield.domain.model.FundHoldingComparisonModel
import com.evangers.stockfield.domain.model.FundModel
import com.evangers.stockfield.domain.model.HistoryModel
import com.evangers.stockfield.domain.model.ListResponseModel
import com.evangers.stockfield.domain.repository.FundRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FundRepositoryImpl @Inject constructor(
    private val stockFieldApi: StockFieldApi,
    private val fundHoldingsMapper: FundHoldingsMapper,
    private val fundMapper: FundMapper,
    private val historyMapper: HistoryMapper
) : FundRepository {

    override suspend fun getFundsFromCompany(companyIndex: Int): ListResponseModel<FundModel> {
        try {
            val response = withContext(Dispatchers.IO) {
                stockFieldApi.getFundsFromCompany(companyIndex)
            }
            return ListResponseModel(
                totalCounts = response.totalCounts,
                list = response.list.map {
                    fundMapper.mapFromEntity(it)
                }
            )
        } catch (exception: Exception) {
            throw exception
        }
    }

    override suspend fun getFundComparison(
        page: Int,
        itemsPerPage: Int?,
        fund: String,
        dateFrom: String?,
        dateTo: String?,
        order: String
    ): ListResponseModel<FundHoldingComparisonModel> {
        val response = withContext(Dispatchers.IO) {
            stockFieldApi.getFundComparison(
                fundName = fund,
                dateFrom = dateFrom,
                dateTo = dateTo,
                order = order,
                page = page,
                perPage = itemsPerPage
            )
        }
        return ListResponseModel(
            totalCounts = response.totalCounts,
            list = response.list.map {
                fundHoldingsMapper.mapFromEntity(it)
            }
        )
    }

    override suspend fun getFundHistory(
        page: Int,
        itemsPerPage: Int?,
        fundName: String,
        ticker: String
    ): ListResponseModel<HistoryModel> {
        val response = withContext(Dispatchers.IO) {
            stockFieldApi.getHistoryFromFund(
                fundName = fundName,
                ticker = ticker,
                page = page,
                perPage = itemsPerPage
            )
        }
        return ListResponseModel(
            totalCounts = response.totalCounts,
            list = response.list.map {
                historyMapper.mapFromEntity(it)
            }
        )
    }

}